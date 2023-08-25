package com.ues.edu.apidecanatoce.servicesImpl.vehiculo;

import com.ues.edu.apidecanatoce.dtos.compras.ProveedorDto;
import com.ues.edu.apidecanatoce.dtos.vehiculo.VehiculoDto;
import com.ues.edu.apidecanatoce.entities.compras.Proveedor;
import com.ues.edu.apidecanatoce.entities.vehiculo.Vehiculo;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.vehiculo.IVehiculoRepository;
import com.ues.edu.apidecanatoce.services.vehiculo.IVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

    @Autowired
    private IVehiculoRepository vehiculoRepository;

    @Override
    public VehiculoDto registrar(VehiculoDto data) {
        if (vehiculoRepository.existsByPlaca(data.getPlaca())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El número de placa ya está registrado");
        }
        return vehiculoRepository.save(data.toEntity()).toDTO();
    }

    @Override
    public VehiculoDto leerPorId(UUID id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentra proveedor"));
        return vehiculo.toDTO();
    }

    @Override
    public Page<VehiculoDto> listar(Pageable pageable) {
        Page<Vehiculo> vehiculos = vehiculoRepository.findAll(pageable);
        return vehiculos.map(Vehiculo::toDTO);
    }

    @Override
    public List<VehiculoDto> listarSinPagina() {
        List<Vehiculo> vehiculoList= this.vehiculoRepository.findAll();
        return vehiculoList.stream().map(Vehiculo::toDTO).toList();
    }

    @Override
    public List<VehiculoDto> listarPorClase() {
        return null;
    }

    @Override
    public VehiculoDto actualizar(VehiculoDto data) {
        VehiculoDto buscarProveedor = leerPorId(data.getCodigoVehiculo());
        if (vehiculoRepository.existsByPlacaAndCodigoVehiculoNot(data.getPlaca(), data.getCodigoVehiculo())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "La placa ya está registrado");
        }
        return vehiculoRepository.save(data.toEntity()).toDTO();
    }

    @Override
    public VehiculoDto eliminar(UUID id) {
        VehiculoDto vehiculoDto = leerPorId(id);
        vehiculoRepository.delete(vehiculoDto.toEntity());
        return vehiculoDto;
    }
}
