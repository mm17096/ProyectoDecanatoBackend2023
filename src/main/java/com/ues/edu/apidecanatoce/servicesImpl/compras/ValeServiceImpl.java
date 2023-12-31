package com.ues.edu.apidecanatoce.servicesImpl.compras;

import com.ues.edu.apidecanatoce.dtos.compras.UsuarioMandarDto;
import com.ues.edu.apidecanatoce.dtos.compras.UsuarioRespuestaDto;
import com.ues.edu.apidecanatoce.dtos.compras.ValeDependeDto;
import com.ues.edu.apidecanatoce.dtos.compras.ValeDto;
import com.ues.edu.apidecanatoce.entities.compras.Compra;
import com.ues.edu.apidecanatoce.entities.compras.Vale;
import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import com.ues.edu.apidecanatoce.entities.logVale.LogVale;
import com.ues.edu.apidecanatoce.entities.usuario.Usuario;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.compras.ICompraRepository;
import com.ues.edu.apidecanatoce.repositorys.compras.IValeRepository;
import com.ues.edu.apidecanatoce.repositorys.empleado.IEmpleadoRepository;
import com.ues.edu.apidecanatoce.repositorys.logVale.ILogValeRepository;
import com.ues.edu.apidecanatoce.repositorys.usuario.IUsuarioRepository;
import com.ues.edu.apidecanatoce.services.compras.IValeService;
import com.ues.edu.apidecanatoce.servicesImpl.estados.EstadosServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.ues.edu.apidecanatoce.entities.usuario.Role.JEFE_FINANACIERO;

@Service
@RequiredArgsConstructor
public class ValeServiceImpl implements IValeService {

    private final IValeRepository valeRepository;
    private final ICompraRepository compraRepository;
    private final ILogValeRepository logValeRepository;

    private final IUsuarioRepository usuarioRepository;
    private final IEmpleadoRepository empleadoRepository;
    private final EstadosServiceImpl estadosService;
    private final PasswordEncoder passwordEncoder;

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
    public List<ValeDependeDto> obtenerValesPorCompra(UUID idCompra) {
        List<Vale> valesPorCompra = valeRepository.findByCompraId(idCompra);

        valesPorCompra.sort(Comparator.comparing(Vale::getCorrelativo));
        return valesPorCompra.stream()
                .map(Vale::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ValeDependeDto> obtenerValesPorEstado(int estado) {
        List<Vale> valesPorCompra = valeRepository.findByEstado(estado);

        valesPorCompra.sort(Comparator.comparing(Vale::getCorrelativo));
        return valesPorCompra.stream()
                .map(Vale::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public int obtenerCantidadValesPorEstado(int estado) {
        List<Vale> valesPorCompra = valeRepository.findByEstado(estado);
        return valesPorCompra.size(); // Retorna la cantidad de elementos en la lista
    }

    @Override
    public Page<ValeDependeDto> listar(Pageable pageable) {
        Page<Vale> vales = valeRepository.findAll(pageable);
        return vales.map(Vale::toDTO);
    }

    @Override
    public List<ValeDependeDto> listarSinPagina() {
        List<Vale> veles = this.valeRepository.findAll();
        return veles.stream().map(Vale::toDTO).toList();
    }

    @Override
    public ValeDependeDto actualizar(UUID id, ValeDto data) {
        ValeDependeDto buscarVale = leerPorId(id);
        data.setId(id);
        return valeRepository.save(data.toEntityComplete(compraRepository)).toDTO();
    }

    @Override
    public List<ValeDependeDto> actualizarTodosValesPorCantidad(List<ValeDependeDto> data, String concepto, String idusuariologueado) {
        List<ValeDependeDto> valesActualizados = new ArrayList<>();
        LocalDateTime fechaActual = LocalDateTime.now();
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
                logEntity.setActividad(concepto);
                logEntity.setUsuario(idusuariologueado);
                logEntity.setVale(vale);
                logValeRepository.save(logEntity);
            }
            valeDto.setEstado(9);
            valesActualizados.add(valeDto);
        }
        return valesActualizados;
    }

    @Override
    public UsuarioRespuestaDto validarUsuario(UsuarioMandarDto data) {
        Usuario usuario = OptenerUsuario(data.getNombre());
        if (usuario.getCodigoUsuario() == null) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El usuario no existe");
        }

        Empleado empleado = empleadoRepository.findById(usuario.getEmpleado().getCodigoEmpleado()).orElse(null);

        if (!passwordEncoder.matches(data.getClave(), usuario.getPassword())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "La contraseña no coincide");
        }

        if (empleado.getEstado() != estadosService.leerPorNombre("Activo").getCodigoEstado()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El usuario esta inactivo");
        }

        if (!Objects.equals(usuario.getRole(), JEFE_FINANACIERO)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Este usuario no es jefe de unidad financiera");
        }

        return UsuarioRespuestaDto.builder()
                .codigoUsuario(usuario.getCodigoUsuario())
                .empleado(empleado)
                .build();
    }

    public Usuario OptenerUsuario(String nombre) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        Usuario usuariOb = new Usuario();
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                usuariOb = usuario;
            }
        }
        return usuariOb;
    }

    @Override
    public ValeDependeDto eliminar(UUID id) {
        ValeDependeDto vale = leerPorId(id);
        valeRepository.delete(vale.toEntitySaveDep());
        return vale;
    }
}
