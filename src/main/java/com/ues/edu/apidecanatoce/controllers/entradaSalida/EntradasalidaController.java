package com.ues.edu.apidecanatoce.controllers.entradaSalida;
import com.ues.edu.apidecanatoce.dtos.entradasalidaDto.EntradasalidaDto;
import com.ues.edu.apidecanatoce.dtos.entradasalidaDto.EntradasalidaPeticionDto;
import com.ues.edu.apidecanatoce.services.Ientradasalidaservice;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/entradasalida")
public class EntradasalidaController {

    private final Ientradasalidaservice ientradasalidaservice;

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

}
