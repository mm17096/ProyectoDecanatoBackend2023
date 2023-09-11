package com.ues.edu.apidecanatoce.controllers.usuario.autenticacion;

import com.ues.edu.apidecanatoce.Jwt.JwtService;
import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import com.ues.edu.apidecanatoce.entities.usuario.Usuario;
import com.ues.edu.apidecanatoce.repositorys.empleado.IEmpleadoRepository;
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
@RequestMapping("/usuario/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final UsuarioServiceImpl authService;

    private final IEmpleadoRepository empleadoRepository;

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

    // Endpoint para renovar el token
    @GetMapping("/renew")
    public ResponseEntity<AuthResponse> renewToken(HttpServletRequest request) {
        // Obtén el token de la cabecera 'x-token'
        String token = request.getHeader("x-token");

        if (token != null && jwtTokenUtil.validateToken(token)) {
            // El token es válido, genera un nuevo token y devuélvelo
            UserDetails userDetails = jwtTokenUtil.getUserDetailsFromToken(token);
            Usuario usuario = authService.OptenerUsuario(userDetails.getUsername());
            Empleado empleado = empleadoRepository.findById(usuario.getEmpleado().getCodigoEmpleado()).orElse(null);
            String newToken = jwtTokenUtil.getToken(userDetails);

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