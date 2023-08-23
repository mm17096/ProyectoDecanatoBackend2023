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

@Service
@RequiredArgsConstructor
public class DetalleAsignacionServiceImpl implements IDetalleAsignacionService {

    private final IDetalleAsignacionRepository iDetalleAsignacionRepository;

    private final DetalleAsignacionMapper detalleAsignacionMapper;

    @Override
    public Page<DetalleAsignacionDto> list(Pageable pageable) {
        var detalles = iDetalleAsignacionRepository.findAll(pageable);
        return detalles.map(detalleAsignacionMapper);
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
    public DetalleAsignacionVale leerPorDUI(String dui) {
        return null;
    }

    @Override
    public boolean eliminar(DetalleAsignacionVale obj) {
        return false;
    }

    @Override
    public DetalleAsignacionDto leerPorDUI(String dui) {
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
