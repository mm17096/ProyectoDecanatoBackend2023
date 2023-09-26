package com.ues.edu.apidecanatoce.servicesImpl.solicitudVale;

import com.ues.edu.apidecanatoce.dtos.documentovaleDto.SolicitudvaleDto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.SolicitudValeDependeDto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.SolicitudValeADto;
import com.ues.edu.apidecanatoce.entities.documentoVale.Documentovale;
import com.ues.edu.apidecanatoce.entities.entradaSalida.Entrada_Salidas;
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

import java.util.List;
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

    @Override
    public List<SolicitudValeDependeDto> listarSinPaginas() {
        List<SolicitudVale> documento = this.solicitudValeRepository.findAll();
        return documento.stream().map(SolicitudVale::toDTOt).toList();
    }

    @Override
    public SolicitudVale codigosolicitudvehiculo(UUID id) {
        SolicitudVale buscar= solicitudValeRepository.findBySolicitudVehiculo_CodigoSolicitudVehiculo(id);
        return buscar;
    }

    @Override
    public SolicitudVale actualizar_solicitudvale(UUID id, SolicitudVale solicitudVale) {
        solicitudVale.setIdSolicitudVale(id);
        return solicitudValeRepository.save(solicitudVale);
    }
}
