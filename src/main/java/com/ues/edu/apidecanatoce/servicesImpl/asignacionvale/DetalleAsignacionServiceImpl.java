package com.ues.edu.apidecanatoce.servicesImpl.asignacionvale;

import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.DetalleAsignacionDto;
import com.ues.edu.apidecanatoce.entities.AsignacionVales.DetalleAsignacionVale;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.asignacionvale.IDetalleAsignacionRepository;
import com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo.services.asignacionvale.IDetalleAsignacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DetalleAsignacionServiceImpl implements IDetalleAsignacionService {

    private final IDetalleAsignacionRepository iDetalleAsignacionRepository;


    @Override
    public DetalleAsignacionDto registrar(DetalleAsignacionDto data) {
        return null;
    }

    @Override
    public DetalleAsignacionDto leerPorId(UUID id) {
        DetalleAsignacionVale detalleAsignacionVale = this.iDetalleAsignacionRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro la asignacion"));
        return detalleAsignacionVale.toDTODetalle();
    }

    @Override
    public Page<DetalleAsignacionDto> listar(Pageable pageable) {
        return null;
    }

    @Override
    public DetalleAsignacionDto actualizar(String id, DetalleAsignacionDto data) {
        return null;
    }

    @Override
    public DetalleAsignacionDto eliminar(String id) {
        return null;
    }
}
