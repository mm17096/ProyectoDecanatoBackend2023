package com.ues.edu.apidecanatoce.controllers;

import com.ues.edu.apidecanatoce.entities.AsignacionVales.DetalleAsignacionVale;
import com.ues.edu.apidecanatoce.servicesImpl.DetalleAsignacionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/detalleasignacionVale")
public class DetalleAsignacionValeController {

    private final DetalleAsignacionServiceImpl detalleAsignacionService;

    @PostMapping
    public ResponseEntity<DetalleAsignacionVale> registrar(@RequestBody DetalleAsignacionVale detalleAsignacionVale){
        return new ResponseEntity<>(detalleAsignacionService.registrar(detalleAsignacionVale), HttpStatus.OK);
    }
}
