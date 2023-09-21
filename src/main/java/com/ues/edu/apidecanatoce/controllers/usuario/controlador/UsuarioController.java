package com.ues.edu.apidecanatoce.controllers.usuario.controlador;

import com.ues.edu.apidecanatoce.entities.usuario.Usuario;
import com.ues.edu.apidecanatoce.repositorys.usuario.IUsuarioRepository;
import com.ues.edu.apidecanatoce.servicesImpl.usuario.UsuarioServiceImpl;
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

    public UsuarioController(UsuarioServiceImpl usuarioService, IUsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Usuario> leerPorID(@PathVariable String uuid) {
        return ResponseEntity.ok(usuarioService.leerPorID(uuid));
    }

    @PutMapping("/credenciales")
    public ResponseEntity<Usuario> leerPorID(@RequestBody Usuario usuario) {
        Usuario usuarioM = usuarioService.OptenerUsuario(usuario.getUsername());
        usuarioM.setClave(passwordEncoder.encode(usuario.getClave()));
        return ResponseEntity.ok(usuarioRepository.save(usuarioM));
    }
}
