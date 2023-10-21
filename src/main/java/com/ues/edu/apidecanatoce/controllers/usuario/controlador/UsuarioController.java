package com.ues.edu.apidecanatoce.controllers.usuario.controlador;

import com.ues.edu.apidecanatoce.entities.usuario.Usuario;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.usuario.IUsuarioRepository;
import com.ues.edu.apidecanatoce.servicesImpl.usuario.UsuarioServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {
    private final UsuarioServiceImpl usuarioService;

    private final IUsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private Usuario usuario;

    public UsuarioController(UsuarioServiceImpl usuarioService, IUsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Usuario> leerPorID(@PathVariable String uuid) {
        return ResponseEntity.ok(usuarioService.leerPorID(uuid));
    }

    @GetMapping("/datacards")
    public ResponseEntity<DatosCards> DataCards() {
        return ResponseEntity.ok(usuarioRepository.datoscard());
    }

    @PostMapping("/resetpass")
    public ResponseEntity<Usuario> Resetpass(@RequestBody Respass respass) {
        usuario = new Usuario();
        if(usuarioRepository.existsByEmpleadoCorreo(respass.getCorreo())){
            if(usuarioRepository.existsByEmpleadoDui(respass.getDui())) {
                usuario = usuarioRepository.findByEmpleadoCorreo(respass.getCorreo());
                usuarioRepository.save(usuario);
            }
        }
        if(usuario.getCodigoUsuario() == null){
            throw new CustomException(HttpStatus.BAD_REQUEST, "No existen las credenciales");
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/resetpassEmail")
    public ResponseEntity<Usuario> ResetpassEmail(@RequestBody Respass respass) {
        usuario = new Usuario();
        if(usuarioRepository.existsByEmpleadoCorreo(respass.getCorreo())){
                usuario = usuarioRepository.findByEmpleadoCorreo(respass.getCorreo());
                usuario.setCodigo(passwordEncoder.encode(respass.getCodigo()));
                usuarioRepository.save(usuario);
        }
        if(usuario.getCodigoUsuario() == null){
            throw new CustomException(HttpStatus.BAD_REQUEST, "No existen las credenciales");
        }
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/resetpass/confirmarcode/{codigo}")
    public ResponseEntity<Usuario> Confirmarcode(@PathVariable String codigo) {
        if(!passwordEncoder.matches(codigo, usuario.getCodigo())){
            throw new CustomException(HttpStatus.BAD_REQUEST, "El codigo no coincide");
        }
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/credenciales")
    public ResponseEntity<Usuario> leerPorID(@RequestBody Usuario usuario) {
        Usuario usuarioM = usuarioService.OptenerUsuario(usuario.getUsername());
        if(usuario.isNuevo()){
            usuarioM.setNuevo(false);
        }
        usuarioM.setClave(passwordEncoder.encode(usuario.getClave()));
        return ResponseEntity.ok(usuarioRepository.save(usuarioM));
    }


}
