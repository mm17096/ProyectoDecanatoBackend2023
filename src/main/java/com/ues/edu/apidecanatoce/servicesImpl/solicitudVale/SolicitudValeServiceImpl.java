package com.ues.edu.apidecanatoce.servicesImpl.solicitudVale;

import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.SolicitudValeDependeDto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.SolicitudValeADto;
import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.solicitudVale.ISolicitudValeVRepository;
import com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo.ISolicitudVehiculoRepository;
import com.ues.edu.apidecanatoce.services.solicitudVale.ISolicitudValeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SolicitudValeServiceImpl implements ISolicitudValeService {
    private final ISolicitudValeVRepository solicitudValeRepository;
    private final ISolicitudVehiculoRepository solicitudVehiculoRepository;
    @Override
    public SolicitudValeDependeDto registrar(SolicitudValeADto data) {
        return solicitudValeRepository.save(data.toEntityComplete(solicitudVehiculoRepository)).toDTOt();
    }

    @Override
    public SolicitudValeDependeDto leerPorId(UUID id) {
        SolicitudVale vale = solicitudValeRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro vale"));
        return vale.toDTOt();
    }

    @Override
    public Page<SolicitudValeDependeDto> listar(Pageable pageable) {
        Page<SolicitudVale> vales = solicitudValeRepository.findAll(pageable);
        return vales.map(SolicitudVale::toDTOt);
    }

    @Override
    public SolicitudValeDependeDto actualizar(UUID id, SolicitudValeADto data) {
        SolicitudValeDependeDto buscarVale = leerPorId(id);
        data.setIdSolicitudVale(id);
        return solicitudValeRepository.save(data.toEntityComplete(solicitudVehiculoRepository)).toDTOt();
    }

    @Override
    public SolicitudValeDependeDto eliminar(UUID id) {
        SolicitudValeDependeDto vale = leerPorId(id);
        solicitudValeRepository.delete(vale.toEntitySaveDep());
        return vale;
    }
}
