package com.ues.edu.apidecanatoce.servicesImpl;

import com.ues.edu.apidecanatoce.dtos.vehiculo.VehiculoDto;
import com.ues.edu.apidecanatoce.entities.vehiculo.Vehiculo;
import com.ues.edu.apidecanatoce.repositorys.vehiculo.IVehiculoRepository;
import com.ues.edu.apidecanatoce.services.vehiculo.IVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

    @Autowired
    private IVehiculoRepository vehiculoRepository;


    @Override
    public VehiculoDto registrar(VehiculoDto data) {
        return null;
    }

    @Override
    public VehiculoDto leerPorId(UUID id) {
        return null;
    }

    @Override
    public Page<VehiculoDto> listar(Pageable pageable) {
        return null;
    }

    @Override
    public List<VehiculoDto> listarSinPagina() {
        return null;
    }

    @Override
    public VehiculoDto actualizar(VehiculoDto data) {
        return null;
    }

    @Override
    public VehiculoDto eliminar(UUID id) {
        return null;
    }
}
