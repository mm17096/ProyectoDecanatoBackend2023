package com.ues.edu.apidecanatoce.servicesImpl.ajusteVale;

import com.ues.edu.apidecanatoce.dtos.ajusteVale.AjusteValeDto;
import com.ues.edu.apidecanatoce.entities.ajustesVale.AjusteVale;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.ajusteVale.IAjusteValeRepository;
import com.ues.edu.apidecanatoce.repositorys.compras.IValeRepository;
import com.ues.edu.apidecanatoce.services.ajusteVale.IAjusteValeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AjusteValeServiceImpl implements IAjusteValeService {

    private final IAjusteValeRepository ajusteValeRepository;

    @Override
    public AjusteValeDto registrar(AjusteValeDto data) {
        return ajusteValeRepository.save(data.toEntityComplete()).toDTO();
    }

    @Override
    public AjusteValeDto leerPorId(UUID id) {
        AjusteVale AjusteVale = ajusteValeRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentra AjusteVale"));
        return AjusteVale.toDTO();
    }

    @Override
    public Page<AjusteValeDto> listar(Pageable pageable) {
        Page<AjusteVale> AjusteVales = ajusteValeRepository.findAll(pageable);
        return AjusteVales.map(AjusteVale::toDTO);
    }

    @Override
    public List<AjusteValeDto> listarSinPagina() {
        List<AjusteVale> AjusteVales= this.ajusteValeRepository.findAll();
        return AjusteVales.stream().map(AjusteVale::toDTO).toList();
    }

    @Override
    public AjusteValeDto actualizar(UUID id, AjusteValeDto data) {
        AjusteValeDto buscarAjusteVale = leerPorId(id);
        data.setId(id);
        return ajusteValeRepository.save(data.toEntityComplete()).toDTO();
    }

    @Override
    public AjusteValeDto eliminar(UUID id) {
        AjusteValeDto AjusteVale = leerPorId(id);
        ajusteValeRepository.delete(AjusteVale.toEntityComplete());
        return AjusteVale;
    }
}
