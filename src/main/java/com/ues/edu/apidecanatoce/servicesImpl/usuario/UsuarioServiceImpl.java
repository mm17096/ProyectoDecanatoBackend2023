package com.ues.edu.apidecanatoce.servicesImpl.usuario;

import com.ues.edu.apidecanatoce.DataLoaders.Generalmethods;
import com.ues.edu.apidecanatoce.Jwt.JwtService;
import com.ues.edu.apidecanatoce.controllers.usuario.autenticacion.AuthResponse;
import com.ues.edu.apidecanatoce.controllers.usuario.autenticacion.LoginRequest;
import com.ues.edu.apidecanatoce.controllers.usuario.autenticacion.RegisterRequest;
import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import com.ues.edu.apidecanatoce.entities.usuario.Role;
import com.ues.edu.apidecanatoce.entities.usuario.Usuario;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.empleado.IEmpleadoRepository;
import com.ues.edu.apidecanatoce.repositorys.usuario.IUsuarioRepository;
import com.ues.edu.apidecanatoce.services.usuario.IUsuarioService;
import com.ues.edu.apidecanatoce.servicesImpl.cargo.CargoServiceImpl;
import com.ues.edu.apidecanatoce.servicesImpl.estados.EstadosServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    private final IUsuarioRepository usuarioRepository;
    private final IEmpleadoRepository empleadoRepository;
    private final EstadosServiceImpl estadosService;
    private final CargoServiceImpl cargoService;
    private final Generalmethods generalmethods;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private Usuario usuario;
    private Empleado empleado;

    public UsuarioServiceImpl(IUsuarioRepository usuarioRepository, IEmpleadoRepository empleadoRepository, EstadosServiceImpl estadosService, Generalmethods generalmethods, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, CargoServiceImpl cargoService) {
        this.usuarioRepository = usuarioRepository;
        this.empleadoRepository = empleadoRepository;
        this.estadosService = estadosService;
        this.generalmethods = generalmethods;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.cargoService = cargoService;
    }

    public AuthResponse login(LoginRequest request) {
        usuario = OptenerUsuario(request.getNombre());
        //validamos que el usuario exista y retorne
        if (usuario.getCodigoUsuario() == null) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El usuario no existe");
        }

        empleado = empleadoRepository.findById(usuario.getEmpleado().getCodigoEmpleado()).orElse(null);

        if (!passwordEncoder.matches(request.getClave(), usuario.getPassword())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "La contraseña no coincide");
        }

        if (this.empleado.getEstado() != estadosService.leerPorNombre("Activo").getCodigoEstado()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El usuario esta inactivo");
        }
/*
no se esta usando
        if(usuario.isActivo()){
            throw new CustomException(HttpStatus.BAD_REQUEST, "Ya existe una sesión activa");
        }
*/
        //autenticacion y retorno de datos del usuario
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getNombre(), request.getClave()));
        UserDetails user = usuarioRepository.findByNombre(request.getNombre()).orElseThrow();
        //generacion del token
        String token = jwtService.getToken(user);
        //modificamos el estado de la cuenta del usuario a activa
        // usuario.setActivo(true); no se esta usando
        usuario.setToken(token); //almacenamos el token en base de datos
        usuarioRepository.save(usuario);
        //retorno de datos
        return AuthResponse.builder()
                .codigoUsuario(usuario.getCodigoUsuario())
                .usuario(usuario)
                .empleado(empleado)
                .token(token)
                .build();
    }

    public Usuario OptenerUsuario(String nombre) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        Usuario usuariOb = new Usuario();
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                usuariOb = usuario;
            }
        }
        return usuariOb;
    }

    public AuthResponse register(RegisterRequest request, Empleado empleado) {

        Role rol;

        switch (empleado.getCargo().getNombreCargo()){
            case "JEFE DEPARTAMENTO" : {
                rol = Role.JEFE_DEPTO;
                break;
            }
            case "SECRETARIO DECANATO" : {
                rol = Role.SECR_DECANATO;
                break;
            }
            case "DECANO" : {
                rol = Role.DECANO;
                break;
            }
            case "ASISTENTE FINANCIERA" : {
                rol = Role.ASIS_FINANCIERO;
                break;
            }
            case "JEFE FINANCIERO" : {
                rol = Role.JEFE_FINANACIERO;
                break;
            }
            case "VIGILANTE" : {
                rol = Role.VIGILANTE;
                break;
            }
            case "ADMINISTRADOR" : {
                rol = Role.ADMIN;
                break;
            }
            default:{
                rol = Role.USER;
                break;
            }
        }

        String[] partes = request.getNombre().split("@");
        String nombre = partes[0];

        Usuario user = Usuario.builder()
                .codigoUsuario(generalmethods.generarCodigo())
                .nombre(nombre)
                .clave(passwordEncoder.encode(request.getClave()))
                .nuevo(true)
                //.activo(false)no se esta usando
                .role(rol)
                .empleado(empleado)
                .build();

        usuarioRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    @Override
    public Usuario leerPorID(String id) {
        Usuario usuario = usuarioRepository.findByCodigoUsuario(id);
        return usuario;
    }

    // codigo agregado de prueba
    public void modificar(RegisterRequest request, Empleado empleado) {

        System.out.println("ingreso al modificar");
        Role rol;

        switch (empleado.getCargo().getNombreCargo()) {
            case "JEFE DEPARTAMENTO": {
                rol = Role.JEFE_DEPTO;
                break;
            }
            case "SECRETARIO DECANATO": {
                rol = Role.SECR_DECANATO;
                break;
            }
            case "DECANO": {
                rol = Role.DECANO;
                break;
            }
            case "ASISTENTE FINANCIERA": {
                rol = Role.ASIS_FINANCIERO;
                break;
            }
            case "JEFE FINANCIERO": {
                rol = Role.JEFE_FINANACIERO;
                break;
            }
            case "VIGILANTE": {
                rol = Role.VIGILANTE;
                break;
            }
            case "ADMINISTRADOR": {
                rol = Role.ADMIN;
                break;
            }
            default: {
                rol = Role.USER;
                break;
            }

        }

        String[] partes = request.getNombre().split("@");
        String nombre = partes[0];

        Usuario carga = usuarioRepository.findUsuarioByNombre(nombre);

        /*
        Usuario user = Usuario.builder()
                .codigoUsuario(carga.getCodigoUsuario())
                .nombre(carga.getUsername())
                .clave(carga.getPassword())
                .nuevo(carga.isNuevo())
                //.activo(false)no se esta usando
                .role(rol)
                .codigo(carga.getCodigo())
                .empleado(carga.getEmpleado())
                .token(carga.getToken())
                .build();
*/
                carga.setRole(rol);
        usuarioRepository.save(carga);

    }

    //

}
