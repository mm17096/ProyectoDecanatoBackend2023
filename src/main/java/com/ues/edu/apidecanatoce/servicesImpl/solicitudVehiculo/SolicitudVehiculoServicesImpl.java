package com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo;

import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoDto;
import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoPeticionDtO;
import com.ues.edu.apidecanatoce.entities.estados.Estados;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.empleado.IEmpleadoRepository;
import com.ues.edu.apidecanatoce.repositorys.estados.IEstadosRepository;
import com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo.ISolicitudVehiculoRepository;
import com.ues.edu.apidecanatoce.repositorys.vehiculo.IVehiculoRepository;
import com.ues.edu.apidecanatoce.services.solicitudVehiculo.ISolicitudVehiculoServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SolicitudVehiculoServicesImpl implements ISolicitudVehiculoServices {

    private final ISolicitudVehiculoRepository solicitudVehiculoServices;
    private final IEstadosRepository estadosRepository;
    private final IVehiculoRepository vehiculoRepository;
    private final IEmpleadoRepository empleadoRepository;
    @Override
    public SolicitudVehiculoPeticionDtO registrar(SolicitudVehiculoDto data) {
        System.out.println("datos: "+data);
        return solicitudVehiculoServices.save(data.toEntityComplete(vehiculoRepository, empleadoRepository)).toDto();
    }

    @Override
    public SolicitudVehiculoPeticionDtO leerPorId(UUID id) {
        SolicitudVehiculo solicitudVehiculo = solicitudVehiculoServices.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontró la solicitud de vehículo"));
        return solicitudVehiculo.toDto();
    }

    @Override
    public Page<SolicitudVehiculoPeticionDtO> listar(Pageable pageable) {
        Page<SolicitudVehiculo> solicitudes = solicitudVehiculoServices.findAll(pageable);
        List<Estados> estados = estadosRepository.findAll();

        Map<Integer, String> estadoStringMap = new HashMap<>();
        for (Estados estado: estados) {
            estadoStringMap.put(estado.getCodigoEstado(), estado.getNombreEstado());
        }

        return solicitudes.map(solicitud -> {
            SolicitudVehiculoPeticionDtO dto = solicitud.toDto();
            String estadoAsString = estadoStringMap.get(solicitud.getEstado());
            dto.setEstadoString(estadoAsString);
            return dto;
        });
    }

    @Override
    public Page<SolicitudVehiculoPeticionDtO> listarPorEstado(Integer id, Pageable pageable) {
        Page<SolicitudVehiculo> listSoliVe = solicitudVehiculoServices.findAllByEstado(id, pageable);

        List<Estados> estados = estadosRepository.findAll();

        Map<Integer, String> estadoStringMap = new HashMap<>();
        for (Estados estado: estados) {
            estadoStringMap.put(estado.getCodigoEstado(), estado.getNombreEstado());
        }

        return listSoliVe.map(solicitud -> {
            SolicitudVehiculoPeticionDtO dto = solicitud.toDto();
            String estadoAsString = estadoStringMap.get(solicitud.getEstado());
            dto.setEstadoString(estadoAsString);
            return dto;
        });
    }

    @Override
    public SolicitudVehiculoPeticionDtO modificar(UUID codigoSolicitudVehiculo, SolicitudVehiculoDto data) {
        ///SolicitudVehiculoPeticionDtO buscarSoliVe = leerPorId(codigoSolicitudVehiculo);
        data.setCodigoSolicitudVehiculo(codigoSolicitudVehiculo);
        return solicitudVehiculoServices.save(data.toEntityComplete(vehiculoRepository, empleadoRepository)).toDto();
    }
}
