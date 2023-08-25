package com.ues.edu.apidecanatoce.servicesImpl.compras;

import com.ues.edu.apidecanatoce.dtos.compras.ValeDependeDto;
import com.ues.edu.apidecanatoce.dtos.compras.ValeDto;
import com.ues.edu.apidecanatoce.entities.compras.Vale;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.compras.ICompraRepository;
import com.ues.edu.apidecanatoce.repositorys.compras.IValeRepository;
import com.ues.edu.apidecanatoce.services.compras.IValeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ValeServiceImpl implements IValeService {

    private final IValeRepository valeRepository;
    private final ICompraRepository compraRepository;
    @Override
    public ValeDependeDto registrar(ValeDto data) {
        if (valeRepository.existsByCodigoVale(data.getCodigoVale())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El codigo vale ya está registrado");
        }
        return valeRepository.save(data.toEntityComplete(compraRepository)).toDTO();
    }

    @Override
    public ValeDependeDto leerPorId(UUID id) {
        Vale vale = valeRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro vale"));
        return vale.toDTO();
    }

    @Override
    public Page<ValeDependeDto> listar(Pageable pageable) {
        Page<Vale> vales = valeRepository.findAll(pageable);
        return vales.map(Vale::toDTO);
    }

    @Override
    public ValeDependeDto actualizar(UUID id, ValeDto data) {
        ValeDependeDto buscarVale = leerPorId(id);
        if (valeRepository.existsByCodigoValeAndIdNot(data.getCodigoVale(), id)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El codigo vale ya está registrado");
        }
        data.setId(id);
        return valeRepository.save(data.toEntityComplete(compraRepository)).toDTO();
    }

    @Override
    public ValeDependeDto eliminar(UUID id) {
        ValeDependeDto vale = leerPorId(id);
        valeRepository.delete(vale.toEntitySaveDep());
        return vale;
    }
}
