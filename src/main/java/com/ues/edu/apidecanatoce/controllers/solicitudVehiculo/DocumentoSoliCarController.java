package com.ues.edu.apidecanatoce.controllers;

import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.DocumentoSoliCar;
import com.ues.edu.apidecanatoce.entities.GenericResponse;
import com.ues.edu.apidecanatoce.services.solicitudVehiculo.IDocumentosSoliCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/documentosoli")
@RequiredArgsConstructor
public class DocumentoSoliCarController {
    private final IDocumentosSoliCarService documentosService;

}
