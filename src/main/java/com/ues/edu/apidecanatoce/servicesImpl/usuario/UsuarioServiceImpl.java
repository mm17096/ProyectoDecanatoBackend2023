package com.ues.edu.apidecanatoce.servicesImpl.usuario;

import com.ues.edu.apidecanatoce.DataLoaders.Generalmethods;
import com.ues.edu.apidecanatoce.Jwt.JwtService;
import com.ues.edu.apidecanatoce.controllers.usuario.autenticacion.AuthResponse;
import com.ues.edu.apidecanatoce.controllers.usuario.autenticacion.LoginRequest;
import com.ues.edu.apidecanatoce.controllers.usuario.autenticacion.RegisterRequest;
import com.ues.edu.apidecanatoce.dtos.usuario.UsuarioPeticionDto;
import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import com.ues.edu.apidecanatoce.entities.usuario.Role;
import com.ues.edu.apidecanatoce.entities.usuario.Usuario;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.empleado.IEmpleadoRepository;
import com.ues.edu.apidecanatoce.repositorys.usuario.IUsuarioRepository;
import com.ues.edu.apidecanatoce.services.usuario.IUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuarioRepository usuarioRepository;
    private final IEmpleadoRepository empleadoRepository;
    private final Generalmethods generalmethods;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private Usuario usuario;
    private Empleado empleado;

    public UsuarioServiceImpl(IUsuarioRepository usuarioRepository, IEmpleadoRepository empleadoRepository, Generalmethods generalmethods, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.empleadoRepository = empleadoRepository;
        this.generalmethods = generalmethods;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getNombre(), request.getClave()));
        UserDetails user = usuarioRepository.findByNombre(request.getNombre()).orElseThrow();
        usuario = OptenerUsuario(request.getNombre());
        empleado = empleadoRepository.findById(usuario.getEmpleado().getCodigoEmpleado()).orElse(null);
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .codigoUsuario(usuario.getCodigoUsuario())
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
        Usuario user = Usuario.builder()
                .codigoUsuario(generalmethods.generarCodigo())
                .nombre(request.getNombre())
                .clave(passwordEncoder.encode(request.getClave()))
                .nuevo(true)
                .role(Role.USER)
                .empleado(empleado)
                .build();

        usuarioRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    @Override
    public UsuarioPeticionDto leerPorID(String id) {
        Usuario usuario = usuarioRepository.findByCodigoUsuario(id);
        return usuario.toDTO();
    }

}
