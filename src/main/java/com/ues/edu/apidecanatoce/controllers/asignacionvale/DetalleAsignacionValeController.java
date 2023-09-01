package com.ues.edu.apidecanatoce.controllers.asignacionvale;

import com.ues.edu.apidecanatoce.servicesImpl.asignacionvale.DetalleAsignacionServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/detalleasignacionVale")
public class DetalleAsignacionValeController {

    private final DetalleAsignacionServiceImpl detalleAsignacionService;




}
