package com.ues.edu.apidecanatoce.servicesImpl.compras;

import com.ues.edu.apidecanatoce.dtos.compras.ValeDependeDto;
import com.ues.edu.apidecanatoce.dtos.compras.ValeDto;
import com.ues.edu.apidecanatoce.entities.compras.Proveedor;
import com.ues.edu.apidecanatoce.entities.compras.Vale;
import com.ues.edu.apidecanatoce.entities.logVale.LogVale;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.compras.ICompraRepository;
import com.ues.edu.apidecanatoce.repositorys.compras.IProveedorRepository;
import com.ues.edu.apidecanatoce.repositorys.compras.IValeRepository;
import com.ues.edu.apidecanatoce.repositorys.logVale.ILogValeRepository;
import com.ues.edu.apidecanatoce.services.compras.IValeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ValeServiceImpl implements IValeService {

    private final IValeRepository valeRepository;
    private final ICompraRepository compraRepository;
    private final IProveedorRepository proveedorRepository;
    private final ILogValeRepository logValeRepository;

    @Override
    public ValeDependeDto registrar(ValeDto data) {
        return valeRepository.save(data.toEntityComplete(compraRepository)).toDTO();
    }

    @Override
    public ValeDependeDto leerPorId(UUID id) {
        Vale vale = valeRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro vale"));
        return vale.toDTO();
    }

    @Override
    public List<ValeDependeDto> devolverValesPorCantidad(int cantidad) {
        List<ValeDependeDto> valesDevueltos = new ArrayList<>();
        List<Vale> valesActivos = valeRepository.findValesActivosOrderByFechaCompraCorrelativoWithLimit(cantidad);

        for (Vale vale : valesActivos) {
            valesDevueltos.add(vale.toDTO());
        }
        return valesDevueltos;
    }

    @Override
    public List<ValeDependeDto> obtenerValesPorMontoTotal(double montoTotal) {
        List<Vale> valesActivos = valeRepository.findValesActivosOrderByFechaCompraCorrelativo();

        double sumaValores = 0.0;
        List<ValeDependeDto> valesDtoResultado = new ArrayList<>();

        for (Vale vale : valesActivos) {
            if (sumaValores + vale.getValor() <= montoTotal) {
                sumaValores += vale.getValor();
                valesDtoResultado.add(vale.toDTO());
            } else {
                break; // Rompe el bucle si se alcanza el monto total deseado
            }
        }

        return valesDtoResultado;
    }

    @Override
    public Page<ValeDependeDto> listar(Pageable pageable) {
        Page<Vale> vales = valeRepository.findAll(pageable);
        return vales.map(Vale::toDTO);
    }

    @Override
    public ValeDependeDto actualizar(UUID id, ValeDto data) {
        ValeDependeDto buscarVale = leerPorId(id);
        data.setId(id);
        return valeRepository.save(data.toEntityComplete(compraRepository)).toDTO();
    }

    @Override
    public List<ValeDependeDto> actualizarTodosValesPorCantidad(List<ValeDependeDto> data, UUID idProveedor) {
        List<ValeDependeDto> valesActualizados = new ArrayList<>();
        LocalDate fechaActual = LocalDate.now();
        Proveedor proveedor = proveedorRepository.findById(idProveedor).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentra proveedor"));
        for (ValeDependeDto valeDto : data) {
            Vale vale = valeRepository.findById(valeDto.getId()).orElse(null);
            if (vale != null) {
                // Cambia el estado y le da salida al vale
                vale.setEstado(9);
                vale = valeRepository.save(vale);
                //Insertar log a LogVale
                LogVale logEntity = new LogVale();
                logEntity.setEstadoVale(9);
                logEntity.setFechaLogVale(fechaActual);
                logEntity.setActividad("Devolución a proveedor " + proveedor.getNombre());
                logEntity.setUsuario("N/A");
                logEntity.setVale(vale);
                logValeRepository.save(logEntity);
            }
            valeDto.setEstado(9);
            valesActualizados.add(valeDto);
        }
        return valesActualizados;
    }

    @Override
    public ValeDependeDto eliminar(UUID id) {
        ValeDependeDto vale = leerPorId(id);
        valeRepository.delete(vale.toEntitySaveDep());
        return vale;
    }
}
