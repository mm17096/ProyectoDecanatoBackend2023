package com.ues.edu.apidecanatoce.controllers;

import com.ues.edu.apidecanatoce.entities.DocumentoSoliCar;
import com.ues.edu.apidecanatoce.entities.Documentos;
import com.ues.edu.apidecanatoce.entities.GenericResponse;
import com.ues.edu.apidecanatoce.exceptions.ModeloNotFoundException;
import com.ues.edu.apidecanatoce.services.IDocumentosSoliCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/documentosoli")
@RequiredArgsConstructor
public class DocumentoSoliCarController {
    private final IDocumentosSoliCarService documentosService;
    @GetMapping
    public ResponseEntity<List<DocumentoSoliCar>> mostrarDocumentos(){
        List<DocumentoSoliCar> documentos = this.documentosService.listar();
        return new ResponseEntity<>(documentos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoSoliCar> obtenerDocumentos(@PathVariable("id") Integer idDocument){
        DocumentoSoliCar documentos = this.documentosService.leerPorId(idDocument);
        if (documentos==null){
            throw new ModeloNotFoundException("Vehiculo no encontrado");
        }
        return new ResponseEntity<>(documentos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GenericResponse<DocumentoSoliCar>> guardarDocumentos(@RequestBody DocumentoSoliCar documentos) {
        GenericResponse<DocumentoSoliCar> resp = new GenericResponse<>(0,"Fallo - no pudo guardar la documento", documentos);
        Optional<DocumentoSoliCar> opt = Optional.ofNullable(documentos);
        DocumentoSoliCar documentosEntity = new DocumentoSoliCar();
        if(opt.isPresent()){
            documentosEntity = this.documentosService.registrar(documentos);
            resp.setCode(1);
            resp.setMessage("Exito - se almaceno documentos");
            resp.setResponse(documentosEntity);
        }else{
            resp.setMessage("Fallo - error");
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<GenericResponse<DocumentoSoliCar>> editarDocumentos(@RequestBody DocumentoSoliCar documentos){
        GenericResponse<DocumentoSoliCar> resp = new GenericResponse<>(0,"Fallo - no pudo guardar la docmentos", documentos);
        Optional<DocumentoSoliCar> opt = Optional.ofNullable(this.documentosService.leerPorId(documentos.getCodigoDocumento()));
        DocumentoSoliCar documentosEntity = new DocumentoSoliCar();
        if(opt.isPresent()){
            documentosEntity = this.documentosService.registrar(documentos);
            resp.setCode(1);
            resp.setMessage("Exito - se modifico docmento");
            resp.setResponse(documentosEntity);
        }else{
            resp.setMessage("Fallo - error");
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<DocumentoSoliCar>> eliminarDocumentos(@PathVariable("id") Integer id){
        GenericResponse<DocumentoSoliCar> resp = new GenericResponse<>();
        Optional<DocumentoSoliCar> opt = Optional.ofNullable(this.documentosService.leerPorId(id));
        if (opt.isPresent()){
            if (this.documentosService.eliminar(opt.get())){
                resp.setCode(1);
                resp.setMessage("Exito - Se elimino documento");
                resp.setResponse(opt.get());
            }else{
                resp.setCode(0);
                resp.setMessage("Fallo - no pudo eliminarse documento");
                resp.setResponse(opt.get());
            }
        }else {
            resp.setCode(0);
            resp.setMessage("Fallo - no encuentra vehiculo");
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);

    }
}
