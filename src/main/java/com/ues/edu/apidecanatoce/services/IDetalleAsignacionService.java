package com.ues.edu.apidecanatoce.services;

import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.DetalleAsignacionDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public interface IDetalleAsignacionService extends Icrud<DetalleAsignacionDto>{
     Page<DetalleAsignacionDto> list(Pageable pageable);
}
