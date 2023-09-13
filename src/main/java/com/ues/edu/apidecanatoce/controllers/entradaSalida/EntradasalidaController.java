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

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

   /* @DeleteMapping("/{id}")

    public ResponseEntity<GenericResponse<Entrada_Salidas>> eliminarEntradaSalida(@PathVariable("id") UUID id){
        Optional<Entrada_Salidas> opt= Optional.ofNullable(this.entradasalidaimpl.leerPorId(id));
        GenericResponse<Entrada_Salidas> resp= new GenericResponse<>();
        HttpStatus http= HttpStatus.INTERNAL_SERVER_ERROR;
        if (opt.isPresent()){
            if (this.entradasalidaimpl.eliminar(opt.get())){
                resp.setCode(1);
                resp.setMessage("Exito - se elimio la información");
                resp.setResponse(opt.get());
            }else {
                resp.setCode(0);
                resp.setMessage("Fallo - No puedo eliminarse la información");
                resp.setResponse(opt.get());
            }
        }else {
            resp.setCode(0);
            resp.setMessage("Fallo - No hay información que eliminar");
        }
        return new ResponseEntity<GenericResponse<Entrada_Salidas>>(resp, http);
    }


    @PostMapping(value = "/insertar")
    public  ResponseEntity<GenericResponse<Entrada_Salidas>> guardarEntradaSalida(@RequestBody  Entrada_Salidas entradaSalidas){
        GenericResponse<Entrada_Salidas> resp= new GenericResponse<>(0,"fallo- no pudo almacenarse la nueva información", entradaSalidas);
        Optional<Entrada_Salidas> opt= Optional.ofNullable(entradaSalidas);
        Entrada_Salidas conSelect= new Entrada_Salidas();
        System.out.println(entradaSalidas);
        if (opt.isPresent()){
            try {
                conSelect=this.entradasalidaimpl.registrar(entradaSalidas);
                resp.setCode(1);
                resp.setMessage("Exito-- se almaceno la zona");
                resp.setResponse(conSelect);
            }catch (Exception e){
                resp.setMessage("fallo --error: " + e.getMessage());
            }

        }
        System.out.println(resp);
        return new ResponseEntity<GenericResponse<Entrada_Salidas>>(resp, HttpStatus.OK);
    }



    public Entrada_Salidas guardar(@RequestBody Entrada_Salidas entradaSalidas){
        return this.entradasalidaimpl.registrar(entradaSalidas);
    }*/


}
