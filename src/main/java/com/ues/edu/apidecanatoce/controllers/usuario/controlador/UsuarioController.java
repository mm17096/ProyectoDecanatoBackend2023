package com.ues.edu.apidecanatoce.controllers.usuario.controlador;

import com.ues.edu.apidecanatoce.dtos.usuario.UsuarioPeticionDto;
import com.ues.edu.apidecanatoce.entities.usuario.Usuario;
import com.ues.edu.apidecanatoce.repositorys.usuario.IUsuarioRepository;
import com.ues.edu.apidecanatoce.servicesImpl.usuario.UsuarioServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {
    private final UsuarioServiceImpl usuarioService;



    public UsuarioController(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UsuarioPeticionDto> leerPorID(@PathVariable String uuid) {
        return ResponseEntity.ok(usuarioService.leerPorID(uuid));
    }


}
