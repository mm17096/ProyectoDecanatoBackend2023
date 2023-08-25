package com.ues.edu.apidecanatoce.controllers;

import com.ues.edu.apidecanatoce.entities.Cargo;
import com.ues.edu.apidecanatoce.entities.Departamento;
import com.ues.edu.apidecanatoce.entities.GenericResponse;
import com.ues.edu.apidecanatoce.services.IDeptoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/depto")
public class DeptoController {
    private IDeptoService deptoService;

    public DeptoController(IDeptoService deptoService){
       this.deptoService = deptoService;
    }

    @GetMapping
    public ResponseEntity<List<Departamento>> showDepto(){
        List<Departamento> obj = this.deptoService.listar();
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento>consultaById(@PathVariable("id") UUID id){
        Departamento obj = this.deptoService.leerPorId(id);

        return new ResponseEntity<Departamento>(obj , HttpStatus.OK);
    }

    @GetMapping(value = "/listar/{estado}")
    public ResponseEntity<List<Departamento>> cargolistado(@PathVariable("estado") Integer estado) {

        List<Departamento> deptos =  this.deptoService.findAllByEstado(estado);
        return new ResponseEntity<List<Departamento>>(deptos,HttpStatus.OK);
    }

    @PostMapping
    public Departamento savePatient( @RequestBody Departamento patient){
        return this.deptoService.registrar(patient);
    }

    @PutMapping
    public ResponseEntity<GenericResponse<Departamento>> editPatient(@RequestBody Departamento patient){
        System.out.println("depto http "+patient.toString());
        Optional<Departamento> opt = Optional.ofNullable(this.deptoService.leerPorId(patient.getCodigoDepto()));
        GenericResponse<Departamento> response;
        Departamento patientResponse;

        if (opt.isPresent()){
            patientResponse = savePatient(patient);
            System.out.println(patient.getNombre() + " ");
            response =  new GenericResponse<Departamento>(1, "Departamento guardado con exito", patientResponse);
            return new ResponseEntity<GenericResponse<Departamento>>(response, HttpStatus.OK);
        } else {
            response = new GenericResponse<Departamento>(0, "Departamento no fue guardado", patient);
            return new ResponseEntity<GenericResponse<Departamento>>(response, HttpStatus.BAD_REQUEST);
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Departamento>> deletePatient(@PathVariable("id") UUID id){
        Optional<Departamento> opt = Optional.ofNullable(this.deptoService.leerPorId(id));
        GenericResponse<Departamento> response =  new GenericResponse<Departamento>();
        HttpStatus http = HttpStatus.OK;

        if (opt.isPresent()){
            if(this.deptoService.eliminar(opt.get())){
                response.setCode(1);
                response.setMessage("Exito - Se elimino el departamento");
                response.setResponse(opt.get());
            } else {
                response.setCode(0);
                response.setMessage("Fallo - No se pudo eliminar el departamento");
                response.setResponse(opt.get());
            }
        } else {
            response.setCode(0);
            response.setMessage("Fallo - No hay producto que eliminar");
        }
        return new ResponseEntity<GenericResponse<Departamento>>(response, http);
    }

}
