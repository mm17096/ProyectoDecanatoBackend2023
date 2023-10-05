package com.ues.edu.apidecanatoce.controllers.asignacionvale;

import com.ues.edu.apidecanatoce.servicesImpl.asignacionvale.DetalleAsignacionServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/detalleasignacionVale")
@PreAuthorize("hasAnyRole('ADMIN','SECR_DECANATO','JEFE_DEPTO','VIGILANTE','DECANO','ASIS_FINANCIERO','USER','JEFE_FINANACIERO')")
public class DetalleAsignacionValeController {

    private final DetalleAsignacionServiceImpl detalleAsignacionService;




}
