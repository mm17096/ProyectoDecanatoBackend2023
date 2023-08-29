package com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo;

import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo.ISolicitudVehiculoRepository;
import com.ues.edu.apidecanatoce.services.solicitudVehiculo.ISolicitudVehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SolicitudVehiculoServiceImpl implements ISolicitudVehiculoService {

    @Autowired
    private final ISolicitudVehiculoRepository solicitudVehiculoService;

    @Override
    public SolicitudVehiculo registrar(SolicitudVehiculo obj) {
        return this.solicitudVehiculoService.save(obj);
    }

    @Override
    public SolicitudVehiculo modificar(SolicitudVehiculo obj) {
        return this.solicitudVehiculoService.save(obj);
    }

    @Override
    public List<SolicitudVehiculo> listar() {
        List<SolicitudVehiculo> listSolicitudes = this.solicitudVehiculoService.findAll();
        return listSolicitudes;
    }

    @Override
    public SolicitudVehiculo leerPorId(UUID id) {
        return this.solicitudVehiculoService.findById(id).orElseThrow();
    }

    @Override
    public boolean eliminar(SolicitudVehiculo obj) {
        try{
            this.solicitudVehiculoService.delete(obj);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public List<SolicitudVehiculo> listarPorEstado(int estado) {
        //return this.solicitudVehiculoService.findAllByEstado(estado);
        return null;
    }
}
