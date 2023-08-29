package com.ues.edu.apidecanatoce.controllers;


import com.ues.edu.apidecanatoce.repositorys.estados.IEstadosRepository;
import com.ues.edu.apidecanatoce.services.solicitudVehiculo.ISolicitudVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/solicitudVv")
@CrossOrigin("*")
public class SolicitudVvConsultasController {
  /*  @Autowired
    private SolicitudVvService solicitudVvService;

    @GetMapping()
    public ResponseEntity<List<SolicitudVvDTO>> getAllSolicitudVehiculoWithEmpleadover(){
        List<SolicitudVvDTO> solicitudVv = solicitudVvService.getAllSolicitudVehiculoWithEmpleado();
        return ResponseEntity.ok(solicitudVv);
    }*/


    private final ISolicitudVehiculoService servicioSolicitudVehiculo;

    private final IEstadosRepository estadosRepository;

    @Autowired
    public SolicitudVvConsultasController(ISolicitudVehiculoService servicioSolicitudVehiculo, IEstadosRepository estadosRepository) {
        this.servicioSolicitudVehiculo = servicioSolicitudVehiculo;
        this.estadosRepository = estadosRepository;
    }


    /*@GetMapping
    public ResponseEntity<List<SolicitudVvDTO>> listaSolicitudesDTO() throws IOException {
        List<SolicitudVehiculo> soliVehiculos = this.servicioSolicitudVehiculo.listar();
        List<SolicitudVvDTO> soliVehiculosDTOResp = new ArrayList<>();
        List<Estados> estados = estadosRepository.findAll();

        for (SolicitudVehiculo soliVe: soliVehiculos){
            SolicitudVvDTO soliVeDTOResp = new SolicitudVvDTO();
          //  soliVeDTOResp.setCodigoSolicitudVehiculo(soliVe.getCodigoSolicitudVehiculo());
            soliVeDTOResp.setFechaSolicitud(soliVe.getFechaSolicitud());
            soliVeDTOResp.setFechaSalida(soliVe.getFechaSalida());
            soliVeDTOResp.setUnidadSolicitante(soliVe.getUnidadSolicitante());
            soliVeDTOResp.setVehiculo(soliVe.getVehiculo());
            soliVeDTOResp.setObjetivoMision(soliVe.getObjetivoMision());
            soliVeDTOResp.setLugarMision(soliVe.getLugarMision());
            soliVeDTOResp.setDireccion(soliVe.getDireccion());
            soliVeDTOResp.setHoraEntrada(soliVe.getHoraEntrada());
            soliVeDTOResp.setHoraSalida(soliVe.getHoraSalida());
            soliVeDTOResp.setCantidadPersonas(soliVe.getCantidadPersonas());
            // soliVeDTOResp.setListaPasajeros(soliVe.getListaPasajeros());
            soliVeDTOResp.setSolicitante(soliVe.getUsuario());
            soliVeDTOResp.setNombreJefeDepto(soliVe.getJefeDepto());
            soliVeDTOResp.setFechaEntrada(soliVe.getFechaEntrada());

            for (Estados estado: estados){
                if (soliVe.getEstado() == estado.getCodigoEstado()){
                    soliVeDTOResp.setEstado(estado.getNombreEstado());
                }
            }

            soliVeDTOResp.setMotorista(soliVe.getMotorista());
            // soliVeDTOResp.setListDocumentos(soliVe.getListDocumentos());

            soliVehiculosDTOResp.add(soliVeDTOResp);
        }
        return new ResponseEntity<List<SolicitudVvDTO>>(soliVehiculosDTOResp, HttpStatus.OK);
    }*/
}
