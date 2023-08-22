package com.ues.edu.apidecanatoce.controllers;
import com.ues.edu.apidecanatoce.entities.Documentos;
import com.ues.edu.apidecanatoce.services.IDocumentoESservice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/document")
public class DocumentosESController {

    private final IDocumentoESservice idocumentoESservice;

    public DocumentosESController(IDocumentoESservice idocumentoESservice) {
        this.idocumentoESservice = idocumentoESservice;
    }

    @GetMapping
    public ResponseEntity<List<Documentos>> mostrarDocumentosES(){
        List<Documentos> documentosList= this.idocumentoESservice.listar();
        return new ResponseEntity<List<Documentos>>(documentosList, HttpStatus.OK);
    }


}
