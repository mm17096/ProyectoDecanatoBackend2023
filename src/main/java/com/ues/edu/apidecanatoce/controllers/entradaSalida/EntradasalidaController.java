package com.ues.edu.apidecanatoce.controllers.entradaSalida;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.solicitudes.EmpleadosCorreosSolicitudesDto;
import com.ues.edu.apidecanatoce.dtos.entradasalidaDto.CorreosESDto;
import com.ues.edu.apidecanatoce.dtos.entradasalidaDto.EntradasalidaDto;
import com.ues.edu.apidecanatoce.dtos.entradasalidaDto.EntradasalidaPeticionDto;
import com.ues.edu.apidecanatoce.entities.entradaSalida.Entrada_Salidas;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.repositorys.entradaSalida.EntradasalidaRepository;
import com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo.ISolicitudVehiculoRepository;
import com.ues.edu.apidecanatoce.services.Ientradasalidaservice;
import com.ues.edu.apidecanatoce.services.asignacionvale.IAsignacionValeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/entradasalida")
@PreAuthorize("hasAnyRole('ADMIN','VIGILANTE', 'ASIS_FINANCIERO','JEFE_FINANACIERO')")
public class EntradasalidaController {

    private final Ientradasalidaservice ientradasalidaservice;
    private final ISolicitudVehiculoRepository iSolicitudvehiculorepository;
    private final EntradasalidaRepository entradasalidarepository;

    //SEGUNDA FORMA DE HACERLO
    @GetMapping
    public ResponseEntity<Page<EntradasalidaPeticionDto>> listar(Pageable pageable) {//correcto
        return ResponseEntity.ok(ientradasalidaservice.listar(pageable));
    }
    @PostMapping( "/insertar")
    public EntradasalidaPeticionDto registrar( @RequestBody EntradasalidaDto entradasalidadto) {//correcto
        return this.ientradasalidaservice.registrar(entradasalidadto);
    }
    @PutMapping("/editar/{id}")
    public ResponseEntity<EntradasalidaDto> actualizar(@PathVariable UUID id, @Valid @RequestBody EntradasalidaDto entradasalida) {
        return ResponseEntity.ok(ientradasalidaservice.actualizar(id, entradasalida));
    }
    @GetMapping("/buscarentradasalida")
    public ResponseEntity<Entrada_Salidas> listaestado(@RequestParam("filtro") int estadi, @RequestParam("tipo") UUID id) {//correcto
        Entrada_Salidas es=this.ientradasalidaservice.listaEstado(estadi, id);
        return ResponseEntity.ok(es);
    }

    @GetMapping("/todas")//esto es para mostrar las card de los vehiculos con estado=5 y con las misiones del dia
    public ResponseEntity<List<SolicitudVehiculo>> ListaporestadoyFecha() {
        return ResponseEntity.ok(iSolicitudvehiculorepository.listaporestadofecha());
    }
    @GetMapping("/list")
    public ResponseEntity<Entrada_Salidas> buscarkilometraje(@RequestParam("filtro") UUID filtro) {//para validar lo del kilometraje
        Entrada_Salidas es=this.entradasalidarepository.mostrandokilometraje(filtro);
        //return this.entradasalidarepository.mostrandokilometraje(filtro);
        return ResponseEntity.ok(es);
    }

    @GetMapping("/correos")
    public ResponseEntity<List<CorreosESDto>> correos() throws Exception {
        return ResponseEntity.ok(ientradasalidaservice.correos());
    }

}
