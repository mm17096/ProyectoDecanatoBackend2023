package com.ues.edu.apidecanatoce.servicesImpl;

import com.ues.edu.apidecanatoce.entities.AsignacionVales.DetalleAsignacionVale;
import com.ues.edu.apidecanatoce.repositorys.IAsignacionValeRepository;
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
    @Override
    public DetalleAsignacionVale registrar(DetalleAsignacionVale obj) {
        return iDetalleAsignacionRepository.save(obj);
    }

    @Override
    public DetalleAsignacionVale modificar(DetalleAsignacionVale obj) {
        return null;
    }

    @Override
    public List<DetalleAsignacionVale> listar() {
        return iDetalleAsignacionRepository.findAll();
    }

    @Override
    public DetalleAsignacionVale leerPorId(Integer id) {
        return null;
    }

    @Override
    public boolean eliminar(DetalleAsignacionVale obj) {
        return false;
    }

    @Override
    public List<DetalleAsignacionVale> listarPorEstado(int estado) {
        return null;
    }

    @Override
    public Page<DetalleAsignacionVale> list(Pageable pageable) {
        return iDetalleAsignacionRepository.findAll(pageable);
    }
}
