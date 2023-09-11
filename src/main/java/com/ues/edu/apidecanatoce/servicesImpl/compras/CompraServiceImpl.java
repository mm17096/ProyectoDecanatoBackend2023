package com.ues.edu.apidecanatoce.servicesImpl.compras;

import com.ues.edu.apidecanatoce.dtos.compras.CompraInsertarDto;
import com.ues.edu.apidecanatoce.dtos.compras.CompraModificarDto;
import com.ues.edu.apidecanatoce.dtos.compras.CompraPeticionDto;
import com.ues.edu.apidecanatoce.entities.compras.Compra;
import com.ues.edu.apidecanatoce.entities.compras.Proveedor;
import com.ues.edu.apidecanatoce.entities.compras.Vale;
import com.ues.edu.apidecanatoce.entities.logVale.LogVale;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.compras.ICompraRepository;
import com.ues.edu.apidecanatoce.repositorys.compras.IProveedorRepository;
import com.ues.edu.apidecanatoce.repositorys.compras.IValeRepository;
import com.ues.edu.apidecanatoce.repositorys.logVale.ILogValeRepository;
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
    private final IValeRepository valeRepository;
    private final IProveedorRepository proveedorRepository;
    private final ILogValeRepository logValeRepository;

    @Override
    public CompraPeticionDto registrar(CompraInsertarDto data) {
        if (compraRepository.existsByFactura(data.getFactura())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "La factura ya está registrada");
        } else if (data.getCod_inicio() > data.getCod_fin()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El código de inicio debe ser inferior al código de fin");
        } else {

            Compra compraInsertar;
            int cantidadVales = (data.getCod_fin() + 1) - data.getCod_inicio();
            compraInsertar = data.toEntityComplete(proveedorRepository);
            compraInsertar.setCantidad(cantidadVales);
            Compra compraEntity = compraRepository.save(compraInsertar);
            Proveedor proveedor = proveedorRepository.findById(data.getProveedor()).orElseThrow(
                    () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentra proveedor"));

            // Obtener la cantidad de vales a crear
            for (int i = data.getCod_inicio(); i <= data.getCod_fin(); i++) {
                Vale valeEntity = new Vale();
                valeEntity.setEstado(8);
                valeEntity.setValor(data.getPrecio_unitario());
                valeEntity.setCorrelativo(i);  // Establecer el correlativo
                valeEntity.setFecha_vencimiento(data.getFecha_vencimiento());
                valeEntity.setCompra(compraEntity);
                valeRepository.save(valeEntity);  // Guardar el vale en la base de datos
                //Insertar log a LogVale
                LogVale logEntity = new LogVale();
                logEntity.setEstadoVale(8);
                logEntity.setFechaLogVale(data.getFecha_compra().toLocalDate());
                logEntity.setActividad("Adquisición de proveedor " + proveedor.getNombre());
                logEntity.setUsuario("N/A");
                logEntity.setVale(valeEntity);
                logValeRepository.save(logEntity);
            }
            return compraEntity.toDTO();
        }
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
