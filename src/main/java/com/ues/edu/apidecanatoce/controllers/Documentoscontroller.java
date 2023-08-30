package com.ues.edu.apidecanatoce.controllers;
import com.ues.edu.apidecanatoce.entities.Documentos;
import com.ues.edu.apidecanatoce.repositorys.Documentosrepo;
import com.ues.edu.apidecanatoce.servicesImpl.Documentoserviceimpl;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/document")
public class Documentoscontroller {

    private final Documentoserviceimpl documentoserviceimpl;
    private final Documentosrepo documentosrepo;


    @GetMapping
    public ResponseEntity<List<Documentos>> mostrarDocumentosES(){
        List<Documentos> documentosList= this.documentoserviceimpl.listar();
        return new ResponseEntity<List<Documentos>>(documentosList, HttpStatus.OK);
    }


}
