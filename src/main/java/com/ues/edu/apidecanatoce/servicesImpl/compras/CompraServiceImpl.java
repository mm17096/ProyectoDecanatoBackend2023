package com.ues.edu.apidecanatoce.servicesImpl.compras;

import com.ues.edu.apidecanatoce.dtos.compras.CompraInsertarDto;
import com.ues.edu.apidecanatoce.dtos.compras.CompraModificarDto;
import com.ues.edu.apidecanatoce.dtos.compras.CompraPeticionDto;
import com.ues.edu.apidecanatoce.entities.compras.Compra;
import com.ues.edu.apidecanatoce.entities.compras.Vale;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.compras.ICompraRepository;
import com.ues.edu.apidecanatoce.repositorys.compras.IProveedorRepository;
import com.ues.edu.apidecanatoce.repositorys.compras.IValeRepository;
import com.ues.edu.apidecanatoce.services.compras.ICompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompraServiceImpl implements ICompraService {

    private final ICompraRepository compraRepository;
    private final IValeRepository valeRepository;
    private final IProveedorRepository proveedorRepository;

    /*@Override
    public CompraPeticionDto registrar(CompraDto data) {
        if (compraRepository.existsByFactura(data.getFactura())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "La factura ya está registrada");
        }
        if (data.getCod_inicio() >= data.getCod_fin()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El código de inicio debe ser inferior al código de de fin");
        }
        return compraRepository.save(data.toEntityComplete(proveedorRepository)).toDTO();
    }*/

    @Override
    public CompraPeticionDto registrar(CompraInsertarDto data) {
        if (compraRepository.existsByFactura(data.getFactura())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "La factura ya está registrada");
        }
        if (data.getCod_inicio() >= data.getCod_fin()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El código de inicio debe ser inferior al código de fin y no pueden ser iguales");
        }

        Compra compraInsertar;

        int cantidadVales = data.getCod_fin() - data.getCod_inicio();
        double total_compra = cantidadVales * data.getPrecio_unitario();

        compraInsertar = data.toEntityComplete(proveedorRepository);
        compraInsertar.setCantidad(cantidadVales);
        compraInsertar.setTotal_compra(total_compra);

        Compra compraEntity = compraRepository.save(compraInsertar);

        // Obtener la cantidad de vales a crear
        for (int i = 0; i < cantidadVales; i++) {
            Vale valeEntity = new Vale();
            long correlativo = data.getCod_inicio() + i;
            valeEntity.setCodigoVale(generarCodigoValeUnico(compraEntity, correlativo));  // Generar un código único para el vale
            valeEntity.setEstado(8);  // Establecer el estado inicial
            valeEntity.setValor(data.getPrecio_unitario());
            valeEntity.setCorrelativo(correlativo);  // Establecer el correlativo

            valeEntity.setCompra(compraEntity);  // Establecer la relación con la compra creada
            valeRepository.save(valeEntity);  // Guardar el vale en la base de datos
        }

        return compraEntity.toDTO();
    }

    private long generarCodigoValeUnico(Compra compraEntity, long correlativo) {
        LocalDateTime fechaCompra = compraEntity.getFecha();

        // Generar un número aleatorio de 3 dígitos
        int numeroAleatorio = generarNumeroAleatorio();

        // Supongamos que el formato del código de vale es "numRandom_yyyyMMdd_correlativo"
        String fechaString = fechaCompra.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return Long.parseLong(numeroAleatorio + fechaString + correlativo);
    }

    private int generarNumeroAleatorio() {
        Random random = new Random();
        return random.nextInt(900) + 100; // Generar un número entre 100 y 999
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
    public CompraPeticionDto actualizar(UUID id, CompraModificarDto data) {
        CompraPeticionDto buscarCompra = leerPorId(id);
        if (compraRepository.existsByFacturaAndIdNot(data.getFactura(), id)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "La factura ya está registrada");
        }
        buscarCompra.setProveedor(proveedorRepository.findById(data.getProveedor()).get().toDTO());
        buscarCompra.setFactura(data.getFactura());
        buscarCompra.setDescripcion(data.getDescripcion());

        return compraRepository.save(buscarCompra.toEntitySave()).toDTO();
    }

    @Override
    public CompraPeticionDto eliminar(UUID id) {
        CompraPeticionDto compra = leerPorId(id);
        compraRepository.delete(compra.toEntitySave());
        return compra;
    }
}
