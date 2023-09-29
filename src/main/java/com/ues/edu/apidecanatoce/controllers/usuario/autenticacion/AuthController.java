package com.ues.edu.apidecanatoce.controllers.usuario.autenticacion;

import com.ues.edu.apidecanatoce.Jwt.JwtService;
import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import com.ues.edu.apidecanatoce.entities.usuario.Usuario;
import com.ues.edu.apidecanatoce.repositorys.empleado.IEmpleadoRepository;
import com.ues.edu.apidecanatoce.repositorys.usuario.IUsuarioRepository;
import com.ues.edu.apidecanatoce.servicesImpl.usuario.UsuarioServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/usuario/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    
    private final UsuarioServiceImpl authService;

    private final IEmpleadoRepository empleadoRepository;

    private final IUsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtTokenUtil;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        Empleado empleado = empleadoRepository.findById(request.getEmpleado()).orElse(null);
        return ResponseEntity.ok(authService.register(request, empleado));
    }
/*
solo para cambiar el estado de la sesion, no se esta usando
    @PutMapping("/sesion")
    public ResponseEntity<?> cambiarSesion(@RequestBody String id) {
        Usuario usuario = usuarioRepository.findByCodigoUsuario(id);
        usuario.setActivo(false);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(true);
    }
*/
    // Endpoint para renovar el token
    @GetMapping("/renew")
    public ResponseEntity<AuthResponse> renewToken(HttpServletRequest request) {
        // Obtén el token de la cabecera 'x-token'
        String token = request.getHeader("x-token");
        UserDetails userDetails = jwtTokenUtil.getUserDetailsFromToken(token);
        Usuario usuario = authService.OptenerUsuario(userDetails.getUsername());

        if (token != null && jwtTokenUtil.isTokenValid(token, userDetails, usuario)) {
            // El token es válido, genera un nuevo token y devuélvelo
            Empleado empleado = empleadoRepository.findById(usuario.getEmpleado().getCodigoEmpleado()).orElse(null);
            String newToken = jwtTokenUtil.getToken(userDetails);
            usuario.setToken(newToken);
            usuarioRepository.save(usuario);

            Map<String, Object> response = new HashMap<>();
            AuthResponse authResponse = new AuthResponse();

            authResponse.setToken(newToken);
            authResponse.setCodigoUsuario(usuario.getCodigoUsuario());
            authResponse.setEmpleado(empleado);

            return ResponseEntity.ok(authResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
