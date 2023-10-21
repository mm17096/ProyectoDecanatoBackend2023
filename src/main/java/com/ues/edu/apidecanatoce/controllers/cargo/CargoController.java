package com.ues.edu.apidecanatoce.controllers.cargo;


import com.ues.edu.apidecanatoce.dtos.cargosDto.CargosDto;
import com.ues.edu.apidecanatoce.entities.cargos.Cargo;
import com.ues.edu.apidecanatoce.services.cargo.ICargoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cargo")
@PreAuthorize("hasAnyRole('ADMIN','SECR_DECANATO','JEFE_DEPTO','VIGILANTE','DECANO','ASIS_FINANCIERO','USER','JEFE_FINANACIERO')")
public class CargoController {

    private final ICargoService cargoService;


   /* public CargoController(ICargoService cargoService) {
        this.cargoService = cargoService;
    }
*/

    @GetMapping("")
    public ResponseEntity<List<CargosDto>> showCargo(){
        List<CargosDto> obj = this.cargoService.listar();
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargosDto>consultaById(@PathVariable("id") UUID id){
        CargosDto obj = this.cargoService.leerPorId(id);

        return new ResponseEntity<CargosDto>(obj , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CargosDto> registrar(@Valid @RequestBody CargosDto data){
        return ResponseEntity.ok(cargoService.registrar(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CargosDto> modificar(@PathVariable UUID id,
                                               @RequestBody CargosDto data){
        return ResponseEntity.ok(cargoService.modificar(id,data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CargosDto> delete(@PathVariable("id") UUID id){
        return ResponseEntity.ok(cargoService.eliminar(id));
    }
    /*
    @GetMapping(value = "/selext/{estado}")
    public ResponseEntity<List<ICargoxEstadoDTO>> cargoXEstado(@PathVariable Integer estado) {

        List<ICargoxEstadoDTO> cargos =  this.cargoService.findCargoByEstado(estado);
        //return new ResponseEntity<List<ICargoxEstadoDTO>>(cargos,HttpStatus.OK);
        return ResponseEntity.ok(cargoService.findCargoByEstado(estado));
    }
*/
    @GetMapping( "/listar/{estado}")
    public ResponseEntity<List<Cargo>> cargolistado(@PathVariable("estado") Integer estado) {

        List<Cargo> cargos =  this.cargoService.findCargoByEstado2(estado);
        return new ResponseEntity<List<Cargo>>(cargos,HttpStatus.OK);
    }

    @GetMapping( "/lstpage")
    public ResponseEntity<Page<CargosDto>> listarPaginable(Pageable pageable) {
        return ResponseEntity.ok(cargoService.listarConPage(pageable));
    }

    @GetMapping( "/name/{name}")
    public ResponseEntity<List<Cargo>> cargoNombre(@PathVariable("name") String name) {

        List<Cargo> cargos =  this.cargoService.findAllByNombreCargo(name);
        return new ResponseEntity<List<Cargo>>(cargos,HttpStatus.OK);
    }

}
