package com.ues.edu.apidecanatoce.controllers.asignacionvale;


import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.AsignacionValeInDto;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.AsignacionValeOutDto;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.DevolucionValeDto;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.LiquidarValesDto;
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

    @PostMapping("/devolver")
    public ResponseEntity<GenericResponse<?>> devolver(@RequestBody DevolucionValeDto listVales){
        String mensaje= "";
        HttpStatus http = HttpStatus.INTERNAL_SERVER_ERROR;
        GenericResponse<?> resp = new GenericResponse<>(0,
                "No se pudo realizar la Devolución", listVales);
        try {
            this.asignacionValeService.devolverVale(listVales);
            resp.setCode(1);
            if (listVales.getValesDevueltos().size() == 1) {
                mensaje = "Vale devuelto!!";
            } else {
                mensaje = "Vales devueltos!!";
            }
            resp.setMessage(mensaje);
            http = HttpStatus.OK;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<GenericResponse<?>>(resp, http);
    }

    @PostMapping("/liquidar")
    public ResponseEntity<GenericResponse<?>> liquidar(@RequestBody LiquidarValesDto listVales){
        String mensaje= "";
        HttpStatus http = HttpStatus.INTERNAL_SERVER_ERROR;
        GenericResponse<?> resp = new GenericResponse<>(0,
                "No se pudo finalizar la Misión", listVales);
        try {
            this.asignacionValeService.liquidarVales(listVales);
            resp.setCode(1);
            resp.setMessage("Misión Finalizada!!");
            http = HttpStatus.OK;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<GenericResponse<?>>(resp, http);
    }
}
