package com.ues.edu.apidecanatoce.controllers;


import com.ues.edu.apidecanatoce.dtos.DepartamentoDto.DepartamentoDto;

import com.ues.edu.apidecanatoce.entities.Departamentos.Departamento;

import com.ues.edu.apidecanatoce.services.IDeptoService;
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
@RequestMapping("/depto")
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

    @PutMapping
    public ResponseEntity<DepartamentoDto> modificar(@RequestBody DepartamentoDto data){
        return ResponseEntity.ok(deptoService.modificar(data));
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

    @GetMapping(value = "listar/page/")
    public ResponseEntity<Page<DepartamentoDto>> listarPaginable(Pageable pageable) {
        return ResponseEntity.ok(deptoService.listarConPage(pageable));
    }

}
