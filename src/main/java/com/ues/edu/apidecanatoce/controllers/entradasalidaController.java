package com.ues.edu.apidecanatoce.controllers;


import com.ues.edu.apidecanatoce.entities.Entrada_Salidas;
import com.ues.edu.apidecanatoce.entities.GenericResponse;
import com.ues.edu.apidecanatoce.services.IEntradaSalidaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/entradasalida")
public class entradasalidaController {

    private final IEntradaSalidaService entradaSalidaService;

    public entradasalidaController(IEntradaSalidaService entradaSalidaService) {
        this.entradaSalidaService = entradaSalidaService;
    }
    private Entrada_Salidas entradaSalidas;

    @GetMapping
    public ResponseEntity<List<Entrada_Salidas>> mostrarEntradaSalidas(){
        List<Entrada_Salidas> entradaSalidas1= this.entradaSalidaService.listar();
        return new ResponseEntity<List<Entrada_Salidas>>(entradaSalidas1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Entrada_Salidas>> eliminarEntradaSalida(@PathVariable("id") String id){
        Optional<Entrada_Salidas> opt= Optional.ofNullable(this.entradaSalidaService.leerPorId(id));
        GenericResponse<Entrada_Salidas> resp= new GenericResponse<>();
        HttpStatus http= HttpStatus.INTERNAL_SERVER_ERROR;
        if (opt.isPresent()){
            if (this.entradaSalidaService.eliminar(opt.get())){
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
                conSelect=this.entradaSalidaService.registrar(entradaSalidas);
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
        return this.entradaSalidaService.registrar(entradaSalidas);
    }
    @PutMapping
    public ResponseEntity<GenericResponse<Entrada_Salidas>> editarEntradaSalida(@RequestBody Entrada_Salidas entradaSalidas) {
        Optional<Entrada_Salidas> opt = Optional.ofNullable(this.entradaSalidaService.leerPorId(entradaSalidas.getCodigoEntradaSalida()));
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
    }

}
