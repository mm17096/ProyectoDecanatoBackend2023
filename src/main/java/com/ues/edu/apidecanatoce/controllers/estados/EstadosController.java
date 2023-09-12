package com.ues.edu.apidecanatoce.controllers.estados;

import com.ues.edu.apidecanatoce.dtos.estados.EstadosDTO;
import com.ues.edu.apidecanatoce.repositorys.estados.IEstadosRepository;
import com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo.services.estados.IEstadosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
@CrossOrigin(origins = "*")
public class EstadosController {

    private final IEstadosService estadosService;
    private final IEstadosRepository estadosRepository;

    public EstadosController(IEstadosService estadosService, IEstadosRepository estadosRepository) {
        this.estadosService = estadosService;
        this.estadosRepository = estadosRepository;
    }

    @GetMapping("/listaSiliVe")
    public ResponseEntity<List<EstadosDTO>> obtenerEstadosSoliVe() {
        List<EstadosDTO> estados = estadosService.estadosSoliVe();
        return ResponseEntity.ok(estados);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<EstadosDTO>> obtenerEstados() {
        List<EstadosDTO> estados = estadosService.estados();
        return ResponseEntity.ok(estados);
    }


    @GetMapping("/PorID/{filtro}")
    public EstadosDTO obtenerEstadoPorID(@PathVariable("filtro") Integer filtro) {
        EstadosDTO estado = estadosService.leerPorId(filtro);
        return estado;
    }

    @GetMapping("/PorNombre/{filtro}")
    public EstadosDTO obtenerEstadoPorNombre(@PathVariable("filtro") String filtro) {
        EstadosDTO estado = estadosService.leerPorNombre(filtro);
        return estado;
    }

}
