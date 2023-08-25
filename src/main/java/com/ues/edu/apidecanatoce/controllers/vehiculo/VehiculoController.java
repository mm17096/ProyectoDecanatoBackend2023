package com.ues.edu.apidecanatoce.controllers;

import com.ues.edu.apidecanatoce.dtos.vehiculo.VehiculoDto;
import com.ues.edu.apidecanatoce.entities.GenericResponse;
import com.ues.edu.apidecanatoce.entities.vehiculo.Vehiculo;
import com.ues.edu.apidecanatoce.exceptions.ModeloNotFoundException;
import com.ues.edu.apidecanatoce.services.vehiculo.IVehiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehiculo")
@RequiredArgsConstructor
public class VehiculoController {

    private final IVehiculoService vehiculoService;

    @GetMapping
    public ResponseEntity<List<Vehiculo>> mostrarVehiculos(){
        List<Vehiculo> vehiculos = this.vehiculoService.listar();
        return new ResponseEntity<>(vehiculos, HttpStatus.OK);
    }

    @GetMapping("/{idVehiculo}")
    public ResponseEntity<Vehiculo> obtenerVehiculo(@PathVariable("idVehiculo") Integer idVehiculo){
        Vehiculo vehiculo = this.vehiculoService.leerPorId(idVehiculo);
        if (vehiculo==null){
            throw new ModeloNotFoundException("Vehiculo no encontrado");
        }
        return new ResponseEntity<>(vehiculo, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GenericResponse<VehiculoDto>> guardarVehiculo(@Valid @RequestBody VehiculoDto vehiculo) {
        GenericResponse<VehiculoDto> resp = new GenericResponse<>(0,"Fallo - no pudo guardar la vehiculo", vehiculo);
        Optional<VehiculoDto> opt = Optional.ofNullable(vehiculo);
        if(opt.isPresent()){
            Vehiculo vehiculoEntity = new Vehiculo();
            vehiculoEntity.setCodigoVehiculo(vehiculo.getCodigoVehiculo());
            vehiculoEntity.setPlaca(vehiculo.getPlaca());
            vehiculoEntity.setModelo(vehiculo.getModelo());
            vehiculoEntity.setMarca(vehiculo.getMarca());
            vehiculoEntity.setClase(vehiculo.getClase());
            vehiculoEntity.setColor(vehiculo.getColor());
            vehiculoEntity.setYear(vehiculo.getYear());
            vehiculoEntity.setFecha_tarjeta(vehiculo.getFecha_tarjeta());
            vehiculoEntity.setCapacidad(vehiculo.getCapacidad());
            vehiculoEntity.setCapacidadTanque(vehiculo.getCapacidadTanque());
            vehiculoEntity.setEstado(vehiculo.getEstado());
            vehiculoEntity.setN_chasis(vehiculo.getN_chasis());
            vehiculoEntity.setN_motor(vehiculo.getN_motor());
            vehiculoEntity.setTipo_gas(vehiculo.getTipo_gas());
            vehiculoEntity.setNombrefoto(vehiculo.getNombrefoto());
            vehiculoEntity.setUrlfoto(vehiculo.getUrlfoto());

            this.vehiculoService.registrar(vehiculoEntity);
            resp.setCode(1);
            resp.setMessage("Exito - se almaceno vehiculo");
            resp.setResponse(vehiculo);
        }else{
            resp.setMessage("Fallo - error");
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping(value = "/inserta", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<GenericResponse<VehiculoDto>> guardardosParametre(@RequestPart(name = "imagen", required = false) MultipartFile imagen,
                                                                            @Valid @RequestPart("vehiculo") VehiculoDto vehiculo){
        GenericResponse<VehiculoDto> resp = new GenericResponse<>(0,"Fallo - no pudo guardar el vehiculo", vehiculo);
        Optional<VehiculoDto> opt = Optional.ofNullable(vehiculo);
        if(opt.isPresent()){
            Vehiculo vehiculoEntity = new Vehiculo();
            vehiculoEntity.setCodigoVehiculo(vehiculo.getCodigoVehiculo());
            vehiculoEntity.setPlaca(vehiculo.getPlaca());
            vehiculoEntity.setModelo(vehiculo.getModelo());
            vehiculoEntity.setMarca(vehiculo.getMarca());
            vehiculoEntity.setClase(vehiculo.getClase());
            vehiculoEntity.setColor(vehiculo.getColor());
            vehiculoEntity.setYear(vehiculo.getYear());
            vehiculoEntity.setFecha_tarjeta(vehiculo.getFecha_tarjeta());
            vehiculoEntity.setCapacidad(vehiculo.getCapacidad());
            vehiculoEntity.setCapacidadTanque(vehiculo.getCapacidadTanque());
            vehiculoEntity.setEstado(vehiculo.getEstado());
            vehiculoEntity.setN_chasis(vehiculo.getN_chasis());
            vehiculoEntity.setN_motor(vehiculo.getN_motor());
            vehiculoEntity.setTipo_gas(vehiculo.getTipo_gas());
            vehiculoEntity.setNombrefoto(vehiculo.getNombrefoto());
            vehiculoEntity.setUrlfoto(vehiculo.getUrlfoto());

            this.vehiculoService.registrar(vehiculoEntity);
            resp.setCode(1);
            resp.setMessage("Exito - se almaceno vehiculo");
            resp.setResponse(vehiculo);
        }else{
            resp.setMessage("Fallo - error");
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    @PutMapping(value = "/edita", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<GenericResponse<VehiculoDto>> editaVehiculo(@RequestPart(name = "imagen", required = false) MultipartFile imagen,
                                                                      @Valid @RequestPart("vehiculo") VehiculoDto vehiculo){
        GenericResponse<VehiculoDto> resp = new GenericResponse<>(0,"Fallo - no pudo edito el vehiculo", vehiculo);
        Optional<VehiculoDto> opt = Optional.ofNullable(vehiculo);
        if(opt.isPresent()){
            Vehiculo vehiculoEntity = new Vehiculo();
            vehiculoEntity.setCodigoVehiculo(vehiculo.getCodigoVehiculo());
            vehiculoEntity.setPlaca(vehiculo.getPlaca());
            vehiculoEntity.setModelo(vehiculo.getModelo());
            vehiculoEntity.setMarca(vehiculo.getMarca());
            vehiculoEntity.setClase(vehiculo.getClase());
            vehiculoEntity.setColor(vehiculo.getColor());
            vehiculoEntity.setYear(vehiculo.getYear());
            vehiculoEntity.setFecha_tarjeta(vehiculo.getFecha_tarjeta());
            vehiculoEntity.setCapacidad(vehiculo.getCapacidad());
            vehiculoEntity.setCapacidadTanque(vehiculo.getCapacidadTanque());
            vehiculoEntity.setEstado(vehiculo.getEstado());
            vehiculoEntity.setN_chasis(vehiculo.getN_chasis());
            vehiculoEntity.setN_motor(vehiculo.getN_motor());
            vehiculoEntity.setTipo_gas(vehiculo.getTipo_gas());
            vehiculoEntity.setNombrefoto(vehiculo.getNombrefoto());
            vehiculoEntity.setUrlfoto(vehiculo.getUrlfoto());

            this.vehiculoService.modificar(vehiculoEntity);
            resp.setCode(1);
            resp.setMessage("Exito - se edito vehiculo");
            resp.setResponse(vehiculo);
        }else{
            resp.setMessage("Fallo - error");
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Vehiculo>> eliminarVehiculo(@PathVariable("id") Integer id){
        GenericResponse<Vehiculo> resp = new GenericResponse<>();
        Optional<Vehiculo> opt = Optional.ofNullable(this.vehiculoService.leerPorId(id));
        if (opt.isPresent()){
            if (this.vehiculoService.eliminar(opt.get())){
                resp.setCode(1);
                resp.setMessage("Exito - Se elimino vehiculo");
                resp.setResponse(opt.get());
            }else{
                resp.setCode(0);
                resp.setMessage("Fallo - no pudo eliminarse vehiculo");
                resp.setResponse(opt.get());
            }
        }else {
            resp.setCode(0);
            resp.setMessage("Fallo - no encuentra vehiculo");
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);

    }
}
