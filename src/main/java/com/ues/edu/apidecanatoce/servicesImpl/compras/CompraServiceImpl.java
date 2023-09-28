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
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompraServiceImpl implements ICompraService {

    private final ICompraRepository compraRepository;
    private final IValeRepository valeRepository;
    private final IProveedorRepository proveedorRepository;
    private final ILogValeRepository logValeRepository;

    @Override
    public CompraPeticionDto registrar(CompraInsertarDto data, String idUsuarioLogueado) {

        if (data.getFactura() != null && !data.getFactura().isEmpty() && compraRepository.existsByFactura(data.getFactura())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "La factura ya está registrada");
        } else if (data.getCodInicio() > data.getCodFin()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El código de inicio debe ser inferior al código de fin");
        } else {

            Compra compraInsertar;
            int cantidadVales = (data.getCodFin() + 1) - data.getCodInicio();
            compraInsertar = data.toEntityComplete(proveedorRepository);
            compraInsertar.setCantidad(cantidadVales);
            Compra compraEntity = compraRepository.save(compraInsertar);
            Proveedor proveedor = proveedorRepository.findById(data.getProveedor()).orElseThrow(
                    () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentra proveedor"));
            for (int i = data.getCodInicio(); i <= data.getCodFin(); i++) {
                //Insertar log a Vales
                Vale valeEntity = new Vale();
                valeEntity.setEstado(8);
                valeEntity.setValor(data.getPrecioUnitario());
                valeEntity.setCorrelativo(i);
                valeEntity.setFechaVencimiento(data.getFechaVencimiento());
                valeEntity.setCompra(compraEntity);
                valeRepository.save(valeEntity);
                //Insertar log a LogVale
                LogVale logEntity = new LogVale();
                logEntity.setEstadoVale(8);
                logEntity.setFechaLogVale(data.getFechaCompra());
                if (compraEntity.getProveedor().getTipo() == 14) {

                    logEntity.setActividad("Adquisición en préstamo a proveedor " + proveedor.getNombre() + "en concepto de:" + compraEntity.getDescripcion());
                } else {
                    logEntity.setActividad("Adquisición en compra a proveedor " + proveedor.getNombre() + " en concepto de: " + compraEntity.getDescripcion());
                }
                logEntity.setUsuario(idUsuarioLogueado);
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
    public List<CompraPeticionDto> listarSinPagina() {
        // Crea un objeto Sort para ordenar por fecha_hora_compra de manera desc (descendente)
        Sort sort = Sort.by(Sort.Direction.DESC, "fechaCompra");
        List<Compra> compras = this.compraRepository.findAll(sort);
        return compras.stream().map(Compra::toDTO).toList();
    }

    @Override
    public CompraPeticionDto actualizar(UUID id, CompraModificarDto data) {
        CompraPeticionDto buscarCompra = leerPorId(id);
        if (data.getFactura() != null && !data.getFactura().isEmpty() && compraRepository.existsByFacturaAndIdNot(data.getFactura(), id)) {
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

    @Override
    public List<CompraPeticionDto> listarComprasPorRangoDeFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            fechaInicio = LocalDate.now();
            fechaFin = LocalDate.now();
        }
        // Añade la hora inicial (00:00:00) a las fechas LocalDate para convertirlas en LocalDateTime
        LocalDateTime fechaInicioConHora = fechaInicio.atStartOfDay();
        LocalDateTime fechaFinConHora = fechaFin.atTime(LocalTime.MAX);

        // Realiza la búsqueda en la base de datos utilizando las fechas procesadas
        List<Compra> compras = compraRepository.findByFechaCompraBetween(fechaInicioConHora, fechaFinConHora);

        // Convierte las entidades Compra a DTOs CompraPeticionDto
        return compras.stream()
                .map(Compra::toDTO) // Suponiendo que Compra tiene un método toDTO para la conversión
                .collect(Collectors.toList());
    }
}
