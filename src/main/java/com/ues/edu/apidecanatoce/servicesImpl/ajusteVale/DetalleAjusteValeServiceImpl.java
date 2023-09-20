package com.ues.edu.apidecanatoce.servicesImpl.ajusteVale;

import com.ues.edu.apidecanatoce.dtos.ajusteVale.DetalleAjusteValeDto;
import com.ues.edu.apidecanatoce.entities.ajustesVale.DetalleAjusteVale;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.ajusteVale.IAjusteValeRepository;
import com.ues.edu.apidecanatoce.repositorys.ajusteVale.IDetalleAjusteValeRepository;
import com.ues.edu.apidecanatoce.repositorys.compras.IValeRepository;
import com.ues.edu.apidecanatoce.services.ajusteVale.IDetalleAjusteValeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DetalleAjusteValeServiceImpl implements IDetalleAjusteValeService {

    private final IDetalleAjusteValeRepository detalleAjusteValeRepository;
    private final IValeRepository valeRepository;
    private final IAjusteValeRepository ajusteValeRepository;
    
    @Override
    public DetalleAjusteValeDto registrar(DetalleAjusteValeDto data) {
        return detalleAjusteValeRepository.save(data.toEntityComplete(ajusteValeRepository, valeRepository)).toDTO();
    }

    @Override
    public DetalleAjusteValeDto leerPorId(UUID id) {
        DetalleAjusteVale DetalleAjusteVale = detalleAjusteValeRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentra DetalleAjusteVale"));
        return DetalleAjusteVale.toDTO();
    }

    @Override
    public Page<DetalleAjusteValeDto> listar(Pageable pageable) {
        Page<DetalleAjusteVale> DetalleAjusteVales = detalleAjusteValeRepository.findAll(pageable);
        return DetalleAjusteVales.map(DetalleAjusteVale::toDTO);
    }

    @Override
    public List<DetalleAjusteValeDto> listarSinPagina() {
        List<DetalleAjusteVale> DetalleAjusteVales= this.detalleAjusteValeRepository.findAll();
        return DetalleAjusteVales.stream().map(DetalleAjusteVale::toDTO).toList();
    }

    @Override
    public DetalleAjusteValeDto actualizar(UUID id, DetalleAjusteValeDto data) {
        DetalleAjusteValeDto buscarDetalleAjusteVale = leerPorId(id);
        data.setId(id);
        return detalleAjusteValeRepository.save(data.toEntityComplete(ajusteValeRepository, valeRepository)).toDTO();
    }

    @Override
    public DetalleAjusteValeDto eliminar(UUID id) {
        DetalleAjusteValeDto DetalleAjusteVale = leerPorId(id);
        detalleAjusteValeRepository.delete(DetalleAjusteVale.toEntityComplete(ajusteValeRepository, valeRepository));
        return DetalleAjusteVale;
    }
}
