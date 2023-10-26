package com.ues.edu.apidecanatoce.controllers.sendgrid;

import com.ues.edu.apidecanatoce.dtos.sendgrid.SendGridDto;
import com.ues.edu.apidecanatoce.entities.sendgrid.Sendgrid;
import com.ues.edu.apidecanatoce.repositorys.sendgrid.ISendGridRepository;
import com.ues.edu.apidecanatoce.services.sendgrid.ISendGridService;
import com.ues.edu.apidecanatoce.services.sendgrid.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/correo")
public class MailController {

    @Autowired
    private MailService mailService;

    private final ISendGridService sendGridService;

    private final ISendGridRepository sendGridRepository;

    public MailController(ISendGridService sendGridService, ISendGridRepository sendGridRepository) {
        this.sendGridService = sendGridService;
        this.sendGridRepository = sendGridRepository;
    }

    @GetMapping("/sendgrid")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Sendgrid> sendgrid() {
        Sendgrid obj = this.sendGridService.listar();
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PutMapping("/editsendgrid")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Sendgrid> editsendgrid(@RequestBody Sendgrid request) {
        return ResponseEntity.ok(this.sendGridRepository.save(request));
    }

    @PostMapping("/enviarmail")
    public ResponseEntity<String> send(@RequestBody BodyEmail request) throws IOException {
        return ResponseEntity.ok(mailService.send(request));
    }
}