package com.ues.edu.apidecanatoce.controllers.asignacionvale;


import com.ues.edu.apidecanatoce.dtos.asignacionValesDto.asignaciones.*;
import com.ues.edu.apidecanatoce.dtos.asignacionValesDto.solicitudes.BuscarSolicitudValeDto;
import com.ues.edu.apidecanatoce.dtos.asignacionValesDto.solicitudes.EmpleadosCorreosSolicitudesDto;
import com.ues.edu.apidecanatoce.dtos.asignacionValesDto.solicitudes.SolicitudAprobarUsuarioDto;
import com.ues.edu.apidecanatoce.dtos.asignacionValesDto.solicitudes.SolicitudValeAprobarDto;
import com.ues.edu.apidecanatoce.dtos.asignacionValesDto.vales.*;
import com.ues.edu.apidecanatoce.entities.GenericResponse;
import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import com.ues.edu.apidecanatoce.services.asignacionvale.IAsignacionValeService;
import com.ues.edu.apidecanatoce.servicesImpl.asignacionvale.AsignacionValeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/asignacionvale")
@PreAuthorize("hasAnyRole('ADMIN','ASIS_FINANCIERO','JEFE_FINANACIERO')")
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
    public ResponseEntity<AsignacionValeInDto> registrar(@RequestBody AsignacionUsuarioDto data) throws Exception {
        AsignacionValeInDto asignacionVale = data.getAsignacionValeInDto();
        String usuario = data.getIdUsuarioLogueado();
        String empleado = data.getEmpleado();
        String cargo = data.getCargo();
        return ResponseEntity.ok(iAsignacionValeService.registrar(asignacionVale, usuario, empleado, cargo));
    }

    @PostMapping("/devolver")
    public ResponseEntity<GenericResponse<?>> devolver(@RequestBody DevolucionValeUsuarioDto data) {
        DevolucionValeDto listVales = data.getValeDevuelto();
        String usuario = data.getUsuario();
        String mensaje = "";
        HttpStatus http = HttpStatus.INTERNAL_SERVER_ERROR;
        GenericResponse<?> resp = new GenericResponse<>(0,
                "No se pudo realizar la Devolución", listVales);
        try {
            this.asignacionValeService.devolverVale(listVales, usuario);
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
    public ResponseEntity<GenericResponse<?>> liquidar(@RequestBody LiquidarValesUsuarioDto data) {
        LiquidarValesDto listVales = data.getValesLiquidados();
        String usuario = data.getUsuario();
        String empleado = data.getEmpleado();
        String cargo = data.getCargo();
        String mensaje = "";
        HttpStatus http = HttpStatus.INTERNAL_SERVER_ERROR;
        GenericResponse<?> resp = new GenericResponse<>(0,
                "No se pudo finalizar la Misión", listVales);
        try {
            this.asignacionValeService.liquidarVales(listVales, usuario, empleado, cargo);
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

    @GetMapping("/listarsolicitudvalecodigo/{codigo}")
    public ResponseEntity<List<ISolicitudValeFiltradasDto>> listarSolicitudValeEstado(@PathVariable UUID codigo) throws Exception {
        return ResponseEntity.ok(iAsignacionValeService.findSolicitudValeByCodigo(codigo));
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

    @GetMapping("/codigoasignacionvale/{id}")
    public ResponseEntity<BuscarAsignacionValeDto> codigoAsignacionVale(@PathVariable UUID id) throws Exception {
        BuscarAsignacionValeDto cantidadValesDto = iAsignacionValeService.codigoAsignacionVale(id);
        return ResponseEntity.ok(cantidadValesDto);
    }

    @PostMapping("/anular")
    public ResponseEntity<AnularMisionDto> anularMision(@RequestBody AnularMisionUsuarioDto data) throws Exception {
        AnularMisionDto anularMisionDto = data.getMisionAnulada();
        String usuario = data.getUsuario();
        String empleado = data.getEmpleado();
        String cargo = data.getCargo();
        return ResponseEntity.ok(iAsignacionValeService.anularMision(anularMisionDto, usuario, empleado, cargo));
    }

    @PostMapping("/solitudaprobar")
    public ResponseEntity<SolicitudValeAprobarDto> actualizarSolicitudAprobar(@RequestBody SolicitudAprobarUsuarioDto data) throws Exception {
        SolicitudValeAprobarDto solicitudValeAprobarDto = data.getSolicitudValeAprobarDto();
        String usuario = data.getIdUsuarioLogueado();
        String cargo = data.getCargo();
        return ResponseEntity.ok(iAsignacionValeService.actualizarSolicitudAprobar(solicitudValeAprobarDto, usuario, cargo));
    }

    @GetMapping("/solicivaleEstado/{estado}")
    public ResponseEntity<Integer> findSoliciVByEstado(@PathVariable int estado) throws Exception {
        return ResponseEntity.ok(iAsignacionValeService.findSoliciVByEstado(estado));
    }

    @GetMapping("/correosfinanciero")
    public ResponseEntity<List<EmpleadosCorreosSolicitudesDto>> correosFinanciero() throws Exception {
        return ResponseEntity.ok(iAsignacionValeService.correosFinanciero());
    }

    @GetMapping("/correobyid/{codigoEmpleado}")
    public ResponseEntity<List<EmpleadosCorreosSolicitudesDto>> correoById(@PathVariable UUID codigoEmpleado) throws Exception {
        return ResponseEntity.ok(iAsignacionValeService.correoById(codigoEmpleado));
    }
}
