package com.ues.edu.apidecanatoce.controllers.sendgrid;

import com.ues.edu.apidecanatoce.controllers.usuario.autenticacion.AuthResponse;
import com.ues.edu.apidecanatoce.controllers.usuario.autenticacion.LoginRequest;
import com.ues.edu.apidecanatoce.services.sendgrid.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/correo")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/enviarmail")
    public ResponseEntity<String> send(@RequestBody BodyEmail request) throws IOException {
        return ResponseEntity.ok(mailService.sendTextEmail(request));
    }


}