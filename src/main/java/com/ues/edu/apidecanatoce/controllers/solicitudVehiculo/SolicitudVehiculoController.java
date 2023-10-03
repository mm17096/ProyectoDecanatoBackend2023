package com.ues.edu.apidecanatoce.controllers.solicitudVehiculo;

import com.ues.edu.apidecanatoce.dtos.estados.EstadosDTO;
import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.*;
import com.ues.edu.apidecanatoce.entities.*;
import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.entities.usuario.Usuario;
import com.ues.edu.apidecanatoce.entities.vehiculo.Vehiculo;
import com.ues.edu.apidecanatoce.repositorys.ConfigSoliVeRepository;
import com.ues.edu.apidecanatoce.repositorys.estados.IEstadosRepository;
import com.ues.edu.apidecanatoce.services.estados.IEstadosService;
import com.ues.edu.apidecanatoce.services.solicitudVehiculo.ILogSoliVeService;
import com.ues.edu.apidecanatoce.services.solicitudVehiculo.ISolicitudVehiculoServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/solicitudvehiculo")
@RequiredArgsConstructor
public class SolicitudVehiculoController {

    @Autowired
    private ConfigSoliVeRepository configuracionRepository;

    private final ISolicitudVehiculoServices servicioSolicitudVehiculo;
    private final IEstadosService estadosService;
    private final ILogSoliVeService logSoliVeService;



    private final IEstadosRepository estadosRepository;
    private SolicitudVehiculo solicitudVehiculo;
    private Empleado motorista;
    private Usuario usuario;
    private Vehiculo vehiculo;

    // listar solicitudes
    @GetMapping("/lista")
    public ResponseEntity<List<SolicitudVehiculoPeticionDtO>> obtenerSolicitudes() throws IOException {
        List<SolicitudVehiculoPeticionDtO> vehiculos = servicioSolicitudVehiculo.listarSinPagina();
        return ResponseEntity.ok(vehiculos);
    }
    @GetMapping("/listasinpagina/{codigoplaca}")
    public ResponseEntity<List<SolicitudVehiculoPeticionDtO>> BuscarPorplaca(@PathVariable String codigoplaca){
        return ResponseEntity.ok(servicioSolicitudVehiculo.listarPorPlaca(codigoplaca));
    }
    // con paginacion
    @GetMapping("/listapage")
    public ResponseEntity<Page<SolicitudVehiculoPeticionDtO>> listar(Pageable pageable) {
        return ResponseEntity.ok(servicioSolicitudVehiculo.listar(pageable));
    }
    // metodo para filtrar las solicitudes segun estado
    @GetMapping("/listapage/{estado}")
    public ResponseEntity<Page<SolicitudVehiculoPeticionDtO>> listaPorEstado(@PathVariable("estado") Integer estado,
                                                                             Pageable pageable) {
        return ResponseEntity.ok(servicioSolicitudVehiculo.listarPorEstado(estado, pageable));
    }

    @GetMapping("/lista/{estado}")
    public ResponseEntity<List<SolicitudVehiculoPeticionDtO>> listaPorEstadoSinPagina(@PathVariable("estado") Integer estado) {
        return ResponseEntity.ok(servicioSolicitudVehiculo.listarPorEstadoSinPagina(estado));
    }

    @GetMapping("/estados")
    public ResponseEntity<List<EstadosDTO>> obtenerEstadosSoliVe() {
        List<EstadosDTO> estados = estadosService.estadosSoliVe();
        return ResponseEntity.ok(estados);
    }

    @GetMapping("/config")
    public ResponseEntity<List<ConfigSoliVe>> obtenerConfiguracion(){
        List<ConfigSoliVe> configSoliVes = this.configuracionRepository.findAll();
        return new ResponseEntity<>(configSoliVes, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<SolicitudVehiculoPeticionDtO> registrarSoliVe(@Valid @RequestBody SolicitudVehiculoDto solicitudVehiculo) {
        return ResponseEntity.ok(servicioSolicitudVehiculo.registrar(solicitudVehiculo));
    }

    @PutMapping("/edit/{codigoSolicitudVehiculo}")
    public ResponseEntity<SolicitudVehiculoPeticionDtO> actualizarSoliVe(@PathVariable UUID codigoSolicitudVehiculo,
                                                                         @Valid @RequestBody ActualizacionSecretariaDTO solicitudVehiculoDto){
        return ResponseEntity.ok(servicioSolicitudVehiculo.modificar(codigoSolicitudVehiculo, solicitudVehiculoDto));
    }

    //@PreAuthorize("hasAnyRole('ADMIN','SECR_DECANATO','DECANO','JEFE_DEPTO')")
    @PutMapping("/estadoupdate")
    public ResponseEntity<SolicitudVehiculoActualizarEstadoDTO> updateEstado(
            @RequestBody SolicitudVehiculoActualizarEstadoDTO data
    ){
        return ResponseEntity.ok(servicioSolicitudVehiculo.updateEstado(data));
    }

    // LISTADO

    // listar solicitudes
    @GetMapping("/listado/{ROL}")
    public ResponseEntity<List<SolicitudVehiculoPeticionDtO>> obtenerSolicitudesRol(
            @PathVariable (name = "ROL") String rol
    ) throws IOException {
        List<SolicitudVehiculoPeticionDtO> vehiculos = servicioSolicitudVehiculo.listarSinPaginaRol(rol);
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/todas")
    public ResponseEntity<List<SolicitudVehiculoPeticionDtO>> listarTodasSolicitudes(){
        List<SolicitudVehiculoPeticionDtO> listSolicitudes = servicioSolicitudVehiculo.listarTodas();
        return ResponseEntity.ok(listSolicitudes);
    }

    /*@PostMapping("/solitudaprobar")
    public ResponseEntity<ActualizarFechaEntradaSoliVeDTO> actualizarSolicitarVehiculoFinalizada(@RequestBody ActualizarFechaEntradaSoliVeDTO cambioestado) throws Exception {
        return ResponseEntity.ok(servicioSolicitudVehiculo.actualizarSolivehiculofinalizada(cambioestado));
    }*/

    @PutMapping("/fecharegreso")
    public ResponseEntity<SoliVeActulizarFechaEntradaDTO>
    actualizar(@RequestBody SoliVeActulizarFechaEntradaDTO fechaEntradaSoliVeDTO) {
        return ResponseEntity.ok(servicioSolicitudVehiculo.updateFechaEntrada(fechaEntradaSoliVeDTO));
    }

    @GetMapping("/log/{id}")
    public ResponseEntity<List<LogSoliVeDTO>> obtenerLogSoli(
            @PathVariable (name = "id") UUID codigoSolicitudVehiculo) throws IOException{
        List<LogSoliVeDTO> log = logSoliVeService.obtenerLog(codigoSolicitudVehiculo);
        return ResponseEntity.ok(log);
    }
}
