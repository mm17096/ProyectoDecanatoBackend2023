package com.ues.edu.apidecanatoce.controllers.asignacionvale;


import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.AsignacionValeInDto;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.AsignacionValeOutDto;
import com.ues.edu.apidecanatoce.entities.GenericResponse;
import com.ues.edu.apidecanatoce.servicesImpl.asignacionvale.AsignacionValeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/asignacionvale")
public class AsignacionValeController {
    private final AsignacionValeServiceImpl asignacionValeService;

    @GetMapping("/listar/{idAsignacion}")
    public ResponseEntity<AsignacionValeOutDto> asignacionesValesByID(@PathVariable UUID idAsignacion, Pageable pageable) throws Exception {
        return ResponseEntity.ok(asignacionValeService.verAsignacionesById(idAsignacion));
    }

    @PostMapping("/insertar")
    public ResponseEntity<GenericResponse<AsignacionValeInDto>> registrar(@RequestBody AsignacionValeInDto asignacionVale) throws Exception {
        HttpStatus http = HttpStatus.INTERNAL_SERVER_ERROR;
        GenericResponse<AsignacionValeInDto> resp = new GenericResponse<AsignacionValeInDto>(0,
                "No se pudo realizar la asignación", asignacionVale);
        try {
            this.asignacionValeService.registrar(asignacionVale);
            resp.setCode(1);
            resp.setMessage("Exito - Asignación realizada !!");
            http = HttpStatus.OK;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<GenericResponse<AsignacionValeInDto>>(resp, http);
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


    /*@DeleteMapping("/eliminar/{id}")
    public ResponseEntity<GenericResponse<AsignacionVale>> eliminar(@PathVariable("id") Integer id) {
=======
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<GenericResponse<AsignacionVale>> eliminar(@PathVariable("id") UUID id) {
>>>>>>> master:src/main/java/com/ues/edu/apidecanatoce/controllers/AsignacionValeController.java
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
    }*/
}
