package com.ues.edu.apidecanatoce.controllers.departamento;


import com.ues.edu.apidecanatoce.dtos.departamentoDto.DepartamentoDto;

import com.ues.edu.apidecanatoce.entities.departamentos.Departamento;

import com.ues.edu.apidecanatoce.services.departamento.IDeptoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/depto")
public class DeptoController {
    private final IDeptoService deptoService;

    @GetMapping
    public ResponseEntity<List<DepartamentoDto>> showCargo(){
        List<DepartamentoDto> obj = this.deptoService.listar();
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoDto>consultaById(@PathVariable("id") UUID id){
        DepartamentoDto obj = this.deptoService.leerPorId(id);

        return new ResponseEntity<DepartamentoDto>(obj , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DepartamentoDto> registrar( @RequestBody DepartamentoDto data){
        return ResponseEntity.ok(deptoService.registrar(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoDto> modificar(@PathVariable UUID id,
                                                     @Valid @RequestBody DepartamentoDto data){
        return ResponseEntity.ok(deptoService.modificar(id,data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DepartamentoDto> delete(@PathVariable("id") UUID id){
        return ResponseEntity.ok(deptoService.eliminar(id));
    }

    @GetMapping(value = "/listar/{estado}")
    public ResponseEntity<List<Departamento>> deptolistado(@PathVariable("estado") Integer estado) {

        List<Departamento> depto =  this.deptoService.findAllByEstado(estado);
        return new ResponseEntity<List<Departamento>>(depto,HttpStatus.OK);
    }

    @GetMapping( "/lstpage")
    public ResponseEntity<Page<DepartamentoDto>> listarPaginable(Pageable pageable) {
        return ResponseEntity.ok(deptoService.listarConPage(pageable));
    }

}
