package com.ues.edu.apidecanatoce.servicesImpl.compras;

import com.ues.edu.apidecanatoce.dtos.compras.ProveedorDto;
import com.ues.edu.apidecanatoce.entities.compras.Proveedor;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.compras.IProveedorRepository;
import com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo.services.compras.IProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements IProveedorService {

    private final IProveedorRepository proveedorRepository;
    @Override
    public ProveedorDto registrar(ProveedorDto data) {
        if (proveedorRepository.existsByNombre(data.getNombre())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El proveedor ya está registrado");
        }
        if (proveedorRepository.existsByTelefono(data.getTelefono())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El número de teléfono ya está registrado");
        }
        return proveedorRepository.save(data.toEntityComplete()).toDTO();
    }

    @Override
    public ProveedorDto leerPorId(UUID id) {
        Proveedor proveedor = proveedorRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentra proveedor"));
        return proveedor.toDTO();
    }

    @Override
    public Page<ProveedorDto> listar(Pageable pageable) {
        Page<Proveedor> proveedors = proveedorRepository.findAll(pageable);
        return proveedors.map(Proveedor::toDTO);
    }

    @Override
    public List<ProveedorDto> listarSinPagina() {
        List<Proveedor> proveedors= this.proveedorRepository.findAll();
        return proveedors.stream().map(Proveedor::toDTO).toList();
    }

    @Override
    public ProveedorDto actualizar(UUID id, ProveedorDto data) {
        ProveedorDto buscarProveedor = leerPorId(id);
        if (proveedorRepository.existsByNombreAndIdNot(data.getNombre(), id)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El nombre ya está registrado");
        }
        if (proveedorRepository.existsByTelefonoAndIdNot(data.getTelefono(), id)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El número de teléfono ya está registrado");
        }
        data.setId(id);
        return proveedorRepository.save(data.toEntityComplete()).toDTO();
    }

    @Override
    public ProveedorDto eliminar(UUID id) {
        ProveedorDto proveedor = leerPorId(id);
        proveedorRepository.delete(proveedor.toEntityComplete());
        return proveedor;
    }
}
