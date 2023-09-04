package com.ues.edu.apidecanatoce.controllers;

import com.ues.edu.apidecanatoce.dtos.compras.ProveedorDto;
import com.ues.edu.apidecanatoce.dtos.entradasalidaDto.EntradasalidaDto;
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
@CrossOrigin(origins = "*")
@RequestMapping("/entradasalida")
public class Entradasalidacontroller {

    private final Ientradasalidaservice ientradasalidaservice;

    //SEGUNDA FORMA DE HACERLO
    @GetMapping
    public ResponseEntity<List<EntradasalidaDto>> listar() {
        List<EntradasalidaDto> entradas = ientradasalidaservice.listarSinPagina();
        return ResponseEntity.ok(entradas);
    }
    @PostMapping(value = "/insertar")
    public ResponseEntity<EntradasalidaDto> registrar(@Valid @RequestBody EntradasalidaDto entradasalidadto) {
        return ResponseEntity.ok(ientradasalidaservice.registrar(entradasalidadto));
    }
    @PutMapping("/{id}")
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
    }
    @PutMapping
    public ResponseEntity<GenericResponse<Entrada_Salidas>> editarEntradaSalida(@RequestBody Entrada_Salidas entradaSalidas) {
        Optional<Entrada_Salidas> opt = Optional.ofNullable(this.entradasalidaimpl.leerPorId(entradaSalidas.getCodigoEntradaSalida()));
        GenericResponse<Entrada_Salidas> resp;
        Entrada_Salidas entradasalidasRespon;
        if(opt.isPresent()) {
            entradasalidasRespon = guardar(entradaSalidas);
            resp = new GenericResponse<Entrada_Salidas>(1,"Datos modificados con exito",entradasalidasRespon);
            return new ResponseEntity<GenericResponse<Entrada_Salidas>>(resp,HttpStatus.OK);
        }else {
            resp = new GenericResponse<Entrada_Salidas>(0,"Error al modificar algunos datos",entradaSalidas);
            return new ResponseEntity<GenericResponse<Entrada_Salidas>>(resp,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

}
