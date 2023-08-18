package com.ues.edu.apidecanatoce.controllers;


import com.ues.edu.apidecanatoce.entities.Cargo;
import com.ues.edu.apidecanatoce.entities.GenericResponse;
import com.ues.edu.apidecanatoce.services.ICargoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cargo")
public class CargoController {

    private final ICargoService cargoService;


    public CargoController(ICargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping
    public ResponseEntity<List<Cargo>> showCargo(){
        List<Cargo> obj = this.cargoService.listar();
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cargo>consultaById(@PathVariable("id") Integer id){
        Cargo obj = this.cargoService.leerPorId(id);

        return new ResponseEntity<Cargo>(obj , HttpStatus.OK);
    }

    @PostMapping
    public Cargo savePatient( @RequestBody Cargo patient){
        return this.cargoService.registrar(patient);
    }

    @PutMapping
    public ResponseEntity<GenericResponse<Cargo>> editPatient(@RequestBody Cargo patient){
        System.out.println("depto http "+patient.toString());
        Optional<Cargo> opt = Optional.ofNullable(this.cargoService.leerPorId(patient.getCodigoCargo()));
        GenericResponse<Cargo> response;
        Cargo patientResponse;

        if (opt.isPresent()){
            patientResponse = savePatient(patient);
            System.out.println(patient.getNombreCargo() + " ");
            response =  new GenericResponse<Cargo>(1, "Departamento guardado con exito", patientResponse);
            return new ResponseEntity<GenericResponse<Cargo>>(response, HttpStatus.OK);
        } else {
            response = new GenericResponse<Cargo>(0, "Departamento no fue guardado", patient);
            return new ResponseEntity<GenericResponse<Cargo>>(response, HttpStatus.BAD_REQUEST);
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Cargo>> deletePatient(@PathVariable("id") Integer id){
        Optional<Cargo> opt = Optional.ofNullable(this.cargoService.leerPorId(id));
        GenericResponse<Cargo> response =  new GenericResponse<Cargo>();
        HttpStatus http = HttpStatus.OK;

        if (opt.isPresent()){
            if(this.cargoService.eliminar(opt.get())){
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
        return new ResponseEntity<GenericResponse<Cargo>>(response, http);
    }



}
