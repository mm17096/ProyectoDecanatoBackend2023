package com.ues.edu.apidecanatoce.servicesImpl;

import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.DetalleAsignacionDto;
import com.ues.edu.apidecanatoce.entities.AsignacionVales.DetalleAsignacionMapper;
import com.ues.edu.apidecanatoce.repositorys.IDetalleAsignacionRepository;
import com.ues.edu.apidecanatoce.services.IDetalleAsignacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DetalleAsignacionServiceImpl implements IDetalleAsignacionService {


    @Override
    public Page<DetalleAsignacionDto> list(Pageable pageable) {
        return null;
    }

    @Override
    public DetalleAsignacionDto registrar(DetalleAsignacionDto obj) {
        return null;
    }

    @Override
    public DetalleAsignacionDto modificar(DetalleAsignacionDto obj) {
        return null;
    }

    @Override
    public List<DetalleAsignacionDto> listar() {
        return null;
    }

    @Override
    public DetalleAsignacionDto leerPorId(UUID id) {
        return null;
    }

    @Override
    public boolean eliminar(DetalleAsignacionDto obj) {
        return false;
    }

    @Override
    public List<DetalleAsignacionDto> listarPorEstado(int estado) {
        return null;
    }
}
