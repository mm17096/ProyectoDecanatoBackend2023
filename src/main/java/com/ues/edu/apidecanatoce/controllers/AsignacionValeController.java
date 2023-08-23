package com.ues.edu.apidecanatoce.controllers;

import com.ues.edu.apidecanatoce.entities.AsignacionVales.AsignacionVale;
import com.ues.edu.apidecanatoce.entities.GenericResponse;
import com.ues.edu.apidecanatoce.servicesImpl.AsignacionValeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/asignacionVale")
public class AsignacionValeController {
    private final AsignacionValeServiceImpl asignacionValeService;

    @GetMapping("/listar")
    public ResponseEntity<List<AsignacionVale>> listar() {
        return new ResponseEntity<List<AsignacionVale>>(asignacionValeService.listar(), HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<AsignacionVale> registrar(@RequestBody AsignacionVale asignacionVale) {
        return new ResponseEntity<AsignacionVale>(asignacionValeService.registrar(asignacionVale), HttpStatus.OK);
    }

   /* @PutMapping("/modificar")
    public ResponseEntity<GenericResponse<AsignacionVale>> modificar(@RequestBody AsignacionVale asignacionVale) {
        Optional<AsignacionVale> opt = Optional.ofNullable(this.asignacionValeService.leerPorId(asignacionVale.getCodigoAsignacion()));
        GenericResponse<AsignacionVale> resp;
        AsignacionVale asignacionValeRegistro;
        if (opt.isPresent()) {
            asignacionValeRegistro = asignacionValeService.modificar(asignacionVale);
            resp = new GenericResponse<AsignacionVale>(1, "Asignación Modificada con exito", asignacionValeRegistro);
            return new ResponseEntity<GenericResponse<AsignacionVale>>(resp, HttpStatus.OK);
        } else {
            resp = new GenericResponse<AsignacionVale>(0, "No se pudo modficiar la Asignación o no se encontró", asignacionVale);
            return new ResponseEntity<GenericResponse<AsignacionVale>>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<GenericResponse<AsignacionVale>> eliminar(@PathVariable("id") Integer id) {
        Optional<AsignacionVale> opt = Optional.ofNullable(this.asignacionValeService.leerPorId(id));
        GenericResponse<AsignacionVale> resp;
        if (opt.isPresent()) {
            if (this.asignacionValeService.eliminar(opt.get())) {
                resp = new GenericResponse<AsignacionVale>(1, "Asignación Eliminada con exito", opt.get());
                return new ResponseEntity<GenericResponse<AsignacionVale>>(resp, HttpStatus.OK);
            } else {
                resp = new GenericResponse<AsignacionVale>(0, "No se pudo eliminar la Asignación", opt.get());
                return new ResponseEntity<GenericResponse<AsignacionVale>>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            resp = new GenericResponse<AsignacionVale>(0, "No se encontró el ID: ", opt.get());
            return new ResponseEntity<GenericResponse<AsignacionVale>>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
