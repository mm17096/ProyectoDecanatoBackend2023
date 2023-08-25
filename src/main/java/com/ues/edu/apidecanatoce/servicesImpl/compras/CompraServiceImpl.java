package com.ues.edu.apidecanatoce.servicesImpl.compras;

import com.ues.edu.apidecanatoce.dtos.compras.CompraDto;
import com.ues.edu.apidecanatoce.dtos.compras.CompraPeticionDto;
import com.ues.edu.apidecanatoce.entities.compras.Compra;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.compras.ICompraRepository;
import com.ues.edu.apidecanatoce.repositorys.compras.IProveedorRepository;
import com.ues.edu.apidecanatoce.services.compras.ICompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompraServiceImpl implements ICompraService {

    private final ICompraRepository compraRepository;

    private final IProveedorRepository proveedorRepository;

    @Override
    public CompraPeticionDto registrar(CompraDto data) {
        if (compraRepository.existsByFactura(data.getFactura())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "La factura ya está registrada");
        }
        if (data.getCod_inicio() >= data.getCod_fin()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El código de inicio debe ser inferior al código de de fin");
        }
        return compraRepository.save(data.toEntityComplete(proveedorRepository)).toDTO();
    }

    @Override
    public CompraPeticionDto leerPorId(UUID id) {
        Compra compra = compraRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro compra"));
        return compra.toDTO();
    }

    @Override
    public Page<CompraPeticionDto> listar(Pageable pageable) {
        Page<Compra> compras = compraRepository.findAll(pageable);
        return compras.map(Compra::toDTO);
    }

    @Override
    public CompraPeticionDto actualizar(UUID id, CompraDto data) {
        CompraPeticionDto buscarCompra = leerPorId(id);
        if (compraRepository.existsByFacturaAndIdNot(data.getFactura(), id)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "La factura ya está registrada");
        }
        data.setId(id);
        return compraRepository.save(data.toEntityComplete(proveedorRepository)).toDTO();
    }

    @Override
    public CompraPeticionDto eliminar(UUID id) {
        CompraPeticionDto compra = leerPorId(id);
        compraRepository.delete(compra.toEntitySave());
        return compra;
    }
}
