package com.ues.edu.apidecanatoce.controllers.asignacionvale;


import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.asignaciones.*;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.solicitudes.BuscarSolicitudValeDto;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.solicitudes.SolicitudValeAprobarDto;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.vales.*;
import com.ues.edu.apidecanatoce.entities.GenericResponse;
import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import com.ues.edu.apidecanatoce.services.asignacionvale.IAsignacionValeService;
import com.ues.edu.apidecanatoce.servicesImpl.asignacionvale.AsignacionValeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/asignacionvale")
public class AsignacionValeController {
    private final AsignacionValeServiceImpl asignacionValeService;

    private final IAsignacionValeService iAsignacionValeService;

    @GetMapping("/listar/{idAsignacion}")
    public ResponseEntity<AsignacionValeOutDto> asignacionesValesByID(@PathVariable UUID idAsignacion, Pageable pageable) throws Exception {
        return ResponseEntity.ok(asignacionValeService.verAsignacionesById(idAsignacion));
    }

    @GetMapping("/ver/{idAsignacion}")
    public ResponseEntity<AsignacionValeDto> asignacionSolicitudValesByID(@PathVariable UUID idAsignacion) throws Exception {
        return ResponseEntity.ok(asignacionValeService.leerPorId(idAsignacion));
    }


    @PostMapping("/insertar")
    public ResponseEntity<AsignacionValeInDto> registrar(@RequestBody AsignacionValeInDto asignacionVale) throws Exception {
        return ResponseEntity.ok(iAsignacionValeService.registrar(asignacionVale));
    }

    @PostMapping("/devolver")
    public ResponseEntity<GenericResponse<?>> devolver(@RequestBody DevolucionValeDto listVales) {
        String mensaje = "";
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
    public ResponseEntity<GenericResponse<?>> liquidar(@RequestBody LiquidarValesDto listVales) {
        String mensaje = "";
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

    @GetMapping("/listarsolicitudvale")
    public ResponseEntity<Page<SolicitudVale>> listarSolicitudVale(Pageable pageable) throws Exception {
        return ResponseEntity.ok(iAsignacionValeService.listarSolicitudVale(pageable));
    }

    @GetMapping("/cantidadvales")
    public ResponseEntity<CantidadValesDto> cantidadVales() throws Exception {
        CantidadValesDto cantidadValesDto = iAsignacionValeService.cantidadVales();
        return ResponseEntity.ok(cantidadValesDto);
    }

    @GetMapping("/listarsolicitudvaleestado/{estado}")
    public ResponseEntity<List<ISolicitudValeFiltradasDto>> listarSolicitudValeEstado(@PathVariable int estado) throws Exception {
        return ResponseEntity.ok(iAsignacionValeService.findSolicitudValeByEstado(estado));
    }

    @GetMapping("/solitudvale/{id}")
    public ResponseEntity<BuscarSolicitudValeDto> cantidadVales(@PathVariable UUID id) throws Exception {
        BuscarSolicitudValeDto cantidadValesDto = iAsignacionValeService.codigoSolictudVale(id);
        return ResponseEntity.ok(cantidadValesDto);
    }

    @GetMapping("/listarvalesasignar/{cantidadVales}")
    public ResponseEntity<List<IValeAsignarDto>> listarValesAsignar(@PathVariable int cantidadVales) throws Exception {
       return ResponseEntity.ok(iAsignacionValeService.lisIValeAsignarDtos(cantidadVales));
    }

    @GetMapping("/listarvalesasignarPage/{cantidadVales}")
    public ResponseEntity<Page<IValeAsignarDto>> listarValesAsignar(@PathVariable int cantidadVales, Pageable pageable) throws Exception {
        return ResponseEntity.ok(iAsignacionValeService.lisIValeAsignarDtosPage(cantidadVales, pageable));
    }

    @GetMapping("/codigoasignacionvale/{id}")
    public ResponseEntity<BuscarAsignacionValeDto> codigoAsignacionVale(@PathVariable UUID id) throws Exception {
        BuscarAsignacionValeDto cantidadValesDto = iAsignacionValeService.codigoAsignacionVale(id);
        return ResponseEntity.ok(cantidadValesDto);
    }

    @PostMapping("/anular")
    public ResponseEntity<AnularMisionDto> anularMision(@RequestBody AnularMisionDto anularMisionDto) throws Exception {
        return ResponseEntity.ok(iAsignacionValeService.anularMision(anularMisionDto));
    }

    @PostMapping("/solitudaprobar")
    public ResponseEntity<SolicitudValeAprobarDto> actualizarSolicitudAprobar(@RequestBody SolicitudValeAprobarDto solicitudValeAprobarDto) throws Exception {
        return ResponseEntity.ok(iAsignacionValeService.actualizarSolicitudAprobar(solicitudValeAprobarDto));
    }
}
