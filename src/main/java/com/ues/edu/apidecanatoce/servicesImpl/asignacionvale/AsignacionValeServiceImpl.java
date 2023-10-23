package com.ues.edu.apidecanatoce.servicesImpl.asignacionvale;

import com.ues.edu.apidecanatoce.dtos.asignacionValesDto.asignaciones.*;
import com.ues.edu.apidecanatoce.dtos.asignacionValesDto.detalles.DetalleAsignacionDto;
import com.ues.edu.apidecanatoce.dtos.asignacionValesDto.detalles.DetalleAsignacionInDto;
import com.ues.edu.apidecanatoce.dtos.asignacionValesDto.solicitudes.*;
import com.ues.edu.apidecanatoce.dtos.asignacionValesDto.vales.*;
import com.ues.edu.apidecanatoce.entities.asignacionVales.AsignacionVale;
import com.ues.edu.apidecanatoce.entities.asignacionVales.DetalleAsignacionVale;
import com.ues.edu.apidecanatoce.entities.compras.Vale;
import com.ues.edu.apidecanatoce.entities.logVale.LogVale;
import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.LogSolicitudVehiculo;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.asignacionvale.IAsignacionValeRepository;
import com.ues.edu.apidecanatoce.repositorys.asignacionvale.IDetalleAsignacionRepository;
import com.ues.edu.apidecanatoce.repositorys.asignacionvale.ISolicitudValeRepository;
import com.ues.edu.apidecanatoce.repositorys.compras.IValeRepository;
import com.ues.edu.apidecanatoce.repositorys.logVale.ILogValeRepository;
import com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo.ILogSoliVeRepository;
import com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo.ISolicitudVehiculoRepository;
import com.ues.edu.apidecanatoce.services.asignacionvale.IAsignacionValeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RequiredArgsConstructor
@Service
public class AsignacionValeServiceImpl implements IAsignacionValeService {

    private final IAsignacionValeRepository asignacionValeRepository;

    private final IDetalleAsignacionRepository detalleAsignacionRepository;

    private final ISolicitudValeRepository solicitudValeRepository;

    private final IValeRepository valeRepository;

    private final ILogSoliVeRepository logSoliVeRepository;

    private final ISolicitudVehiculoRepository solicitudVehiculoRepository;

    private final ILogValeRepository logValeRepository;

    //METODO PARA GUARDAR LA ASIGNACIÓN
    @Override
    @Transactional
    public AsignacionValeInDto registrar(AsignacionValeInDto data, String usario, String empleado, String cargo) {

        // 5 = Asignado para los vales
        //7 = Finalizado para la solicitud
        //8 = Activo para la asignación
        int estadoVales = 5;
        int estadoSolicitud = 5;

        String actividad = "Vales Asignados a Solicitud de vale";

        LogValeDto logVale = new LogValeDto();
        LogSolicitudVehiculo logSolicitudVehiculo = new LogSolicitudVehiculo();
        LocalDateTime fechaActualLog = LocalDateTime.now();


        //Para guardar en la tabla detalle
        DetalleAsignacionInDto detalleAsignacionInDto = new DetalleAsignacionInDto();

        List<UUID> valesAsignar = new ArrayList<>();
        int cantidadVales = data.getCantidadVales();

        String movimientoVale = "";


        //Evaluando su hay vales para asignar
        if (cantidadVales().getValesDisponibles() == 0) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No hay vales para asignar");
        } else {
            // Guardando la Asignación
            try {
                this.asignacionValeRepository.save(data.toAsignacionValeComplete(solicitudValeRepository)).toAsignacionValeDTO();

                //System.out.println("Los Vales: " + data.getIdVales());
                System.out.println("Cantidad de Vales => : " + cantidadVales);

                // Obteniendo la última asignación reistrada
                System.out.println("El código de la asignacion: " + this.asignacionValeRepository.findTopByOrderByIdDesc(data.getSolicitudVale()));
                UUID codigoAsignacion = this.asignacionValeRepository.findTopByOrderByIdDesc(data.getSolicitudVale());

                //Listar cantidad de vales según el número solicitado
                List<IValeAsignarDto> lsitValeAsignarDtos = lisIValeAsignarDtos(cantidadVales);
                // Obtengo el array de los vales a asignar
                for (IValeAsignarDto datos : lsitValeAsignarDtos) {
                    valesAsignar.add(datos.getIdVale());
                }
                System.out.println("Vales según cantidad: " + valesAsignar);

                // Preparo la Dto para guardar en la tabla detalle_asignación
                for (int i = 0; i < valesAsignar.size(); i++) {
                    detalleAsignacionInDto.setCodigoAsignacionVale(codigoAsignacion);
                    detalleAsignacionInDto.setIdVale(valesAsignar.get(i));

                    movimientoVale = data.toAsignacionValeComplete(solicitudValeRepository).getSolicitudVale().getSolicitudVehiculo().getObjetivoMision() + " "
                            + data.toAsignacionValeComplete(solicitudValeRepository).getSolicitudVale().getSolicitudVehiculo().getLugarMision();
                    try {
                        //Cambiar el estado del Vale
                        actualizarEstadoVale(valesAsignar.get(i), estadoVales);
                        // Guardar en la tabla detalle_asignación los id de los vales junto con la ültima asignación reistrada
                        detalleAsignacionRepository.save(detalleAsignacionInDto.toDto(asignacionValeRepository, valeRepository)).toDto();
                        System.out.println("El detalle: " + detalleAsignacionInDto);
                        logVale.setEstadoVale(estadoVales);
                        logVale.setFechaLogVale(fechaActualLog);
                        logVale.setActividad("Vale asignado a la misión " +  movimientoVale);
                        logVale.setVale(valesAsignar.get(i));
                        logVale.setUsuario(usario);
                        logVale(logVale);

                    } catch (Exception e) {
                        throw new CustomException(HttpStatus.BAD_REQUEST, "No se pudo guardar el detalle");
                    }
                }

                //Busca la Solicutud del Vehiculo
                codigoSolicitudVehiculo(data.getSolicitudVale());

                SolicitudVehiculo solicitudVehiculo = this.solicitudVehiculoRepository.findById(codigoSolicitudVehiculo(data.getSolicitudVale()).getCodigoSolicitudVehiculo())
                        .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro la solicitud del vehículo"));

                //cambiar el estado de la solicitud del vale y del vehículo
                actualizarEstadoSolicitud(data.getSolicitudVale(), estadoSolicitud);

                // Actuliza el estado de la Solictud del Vehículo
                actualizarEstadoSolicitudVehiculo(solicitudVehiculo.getCodigoSolicitudVehiculo(), estadoSolicitud);

                SolicitudVale solicitudVale = this.solicitudValeRepository.findById(data.getSolicitudVale()).orElseThrow(
                        () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontró la solicitud: " + data.getSolicitudVale())
                );


                logSolicitudVehiculo.setSoliVale(solicitudVale);
                logSolicitudVehiculo.setEstadoLogSolive(estadoSolicitud);
                logSolicitudVehiculo.setFechaLogSoliVe(fechaActualLog);
                logSolicitudVehiculo.setActividad(actividad);
                logSolicitudVehiculo.setUsuario(empleado);
                logSolicitudVehiculo.setCargo(cargo);
                this.logSoliVeRepository.save(logSolicitudVehiculo);


                // Retornar el mensaje que toso se guardó
                return data;
            } catch (Exception e) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "No se pudo guardar la asignación");
            }
        }


    }

    @Transactional
    @Override
    public AnularMisionDto anularMision(AnularMisionDto data, String usuario, String empleado, String cargo) {
        int estadoVale = 8;
        int estadoSolicitudes = 15;
        UUID codigoDetalleAsignacion;
        LocalDateTime fechaActualLog = LocalDateTime.now();
        String actividad = "Misión anulada";
        LogValeDto logVale = new LogValeDto();
        LogSolicitudVehiculo logSolicitudVehiculo = new LogSolicitudVehiculo();
        try {
            //actualizar el estado de la asignación
            actualizarEstadoAsignacion(data.getCosdigoAsignacion(), estadoSolicitudes);

            //Actualizar el estado de la solicitud del vale
            AsignacionVale asignacionVale = this.asignacionValeRepository.findById(data.getCosdigoAsignacion()).orElseThrow(()
                    -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro la asignacion"));
            actualizarEstadoSolicitud(asignacionVale.getSolicitudVale().getIdSolicitudVale(), estadoSolicitudes);

            //Actualizar el estado de la solicitud del vehículo
            actualizarEstadoSolicitudVehiculo(asignacionVale.getSolicitudVale().getSolicitudVehiculo().getCodigoSolicitudVehiculo(), estadoSolicitudes);


            for (int i = 0; i < data.getValesAsignacion().size(); i++) {
                //Actualizar el estado de la solicitud del vale
                actualizarEstadoVale(data.getValesAsignacion().get(i), estadoVale);

                //Eliminar del detalle de asignación
                codigoDetalleAsignacion = this.asignacionValeRepository.findDetalleAsigancionVale(data.getValesAsignacion().get(i));
                DetalleAsignacionVale detalleAsignacionVale = this.detalleAsignacionRepository.findById(codigoDetalleAsignacion).orElseThrow(()
                        -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro el detalle de la asignación"));
                detalleAsignacionRepository.delete(detalleAsignacionVale);

                //Dejo el registro del movimiento del Vale
                logVale.setEstadoVale(estadoVale);
                logVale.setFechaLogVale(fechaActualLog);
                logVale.setActividad("Vale devuelto sin consumir, misión anulada");
                logVale.setVale(data.getValesAsignacion().get(i));
                logVale.setUsuario(usuario);
                logVale(logVale);
            }

            SolicitudVale solicitudVale = this.solicitudValeRepository.findById(asignacionVale.getSolicitudVale().getIdSolicitudVale()).orElseThrow(
                    () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontró la solicitud: " + asignacionVale.getSolicitudVale().getIdSolicitudVale())
            );


            logSolicitudVehiculo.setSoliVale(solicitudVale);
            logSolicitudVehiculo.setEstadoLogSolive(estadoSolicitudes);
            logSolicitudVehiculo.setFechaLogSoliVe(fechaActualLog);
            logSolicitudVehiculo.setActividad(actividad);
            logSolicitudVehiculo.setUsuario(empleado);
            logSolicitudVehiculo.setCargo(cargo);
            logSolicitudVehiculo.setSoliVe(solicitudVale.getSolicitudVehiculo());
            this.logSoliVeRepository.save(logSolicitudVehiculo);

        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No se pudo anular la misión");
        }

        return data;
    }

    @Override
    public AsignacionValeDto leerPorId(UUID id) {
        AsignacionVale cargo = asignacionValeRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentra el cargo"));
        return cargo.toDTOSolicitud();
    }

    @Override
    public Page<AsignacionValeDto> listar(Pageable pageable) {
        return null;
    }

    //METODO PARA DEVOLVER LOS VALES
    @Transactional
    @Override
    public DevolucionValeDto devolverVale(DevolucionValeDto data, String usuario) {
        LogValeDto logVale = new LogValeDto();
        LocalDateTime fechaActualLog = LocalDateTime.now();
        UUID codigoDetalleAsignacion;
        try {
            for (int i = 0; i < data.getValesDevueltos().size(); i++) {

                //Busco el detalle de la Asignación por medio del codigo del Vale
                codigoDetalleAsignacion = this.asignacionValeRepository.findDetalleAsigancionVale(data.getValesDevueltos().get(i));

                //Elimino el detalle de la Asignación
                DetalleAsignacionVale detalleAsignacionVale = this.detalleAsignacionRepository.findById(codigoDetalleAsignacion).orElseThrow(()
                        -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro el detalle de la asignación"));
                detalleAsignacionRepository.delete(detalleAsignacionVale);

                //Actualizo los estados de los vales
                System.out.println("entra a devolverVale");
                actualizarEstadoVale(data.getValesDevueltos().get(i), data.getEstadoVales());

                //Dejo el registro del movimiento del Vale
                logVale.setEstadoVale(data.getEstadoVales());
                logVale.setFechaLogVale(fechaActualLog);
                logVale.setActividad("Vale devuelto sin consumir en la misión");
                logVale.setVale(data.getValesDevueltos().get(i));
                logVale.setUsuario(usuario);
                logVale(logVale);
            }
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No se pudo devolver el vale");
        }
        return data;
    }

    @Override
    public AsignacionValeDto eliminar(UUID id) {
        return null;
    }

    //METODO PARA VER LAS ASIGNACIONES POR ID
    @Override
    public AsignacionValeOutDto verAsignacionesById(UUID id) {

        AsignacionVale asignacionVale = this.asignacionValeRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro la asignacion"));
        return asignacionVale.toDto(asignacionValeRepository, detalleAsignacionRepository, id);
    }

    @Override
    public DetalleAsignacionDto verDetalleById(UUID id) {
        return null;
    }

    @Override
    public List<IValeAsignarDto> lisIValeAsignarDtos(int cantidadVales) throws IOException {
        if (this.asignacionValeRepository.listarValesAsignar(cantidadVales).isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No hay vales para asignar");
        } else {
            return this.asignacionValeRepository.listarValesAsignar(cantidadVales);
        }

    }

    //ESTE MÉTODO LISTA VALES PARA ASIGNAR PAGINADOS
    @Override
    public Page<IValeAsignarDto> lisIValeAsignarDtosPage(int cantidadVales, Pageable pageable) throws IOException {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();

        // Calcular la cantidad total de elementos disponibles en tu base de datos
        List<IValeAsignarDto> totalElementos = lisIValeAsignarDtos(cantidadVales); // Supongamos que tienes un método count para contar elementos

        // Calcular el número de páginas requerido para la cantidad solicitada de elementos
        int totalPages = (int) Math.ceil((double) totalElementos.size() / pageSize);

        // Asegurarse de que el número de página no sea mayor que el número total de páginas
        if (pageNumber >= totalPages) {
            pageNumber = totalPages - 1;
        }

        // Calcular el offset basado en la página y el tamaño de la página
        int offset = pageNumber * pageSize;

        // Obtener los elementos de la página actual
        List<IValeAsignarDto> lisIValeAsignarPage = asignacionValeRepository.listarValesAsignarPage(offset, pageSize);

        return new PageImpl<>(lisIValeAsignarPage, PageRequest.of(pageNumber, pageSize), totalElementos.size());
    }

    //METODO PARA ACTUALIZAR EL ESTADO DEL VALE
   
    @Override
    public ValeModDto actualizarEstadoVale(UUID id, int estadoVale) {
        System.out.println("entra a actualizarEstadoVale");

        Vale valeEntity = this.valeRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentra el vale"));
        System.out.println("Vales: " + valeEntity);
        if (valeEntity != null) {
            valeEntity.setEstado(estadoVale);
            System.out.println("entra a guardar");
            return valeRepository.save(valeEntity).toValeModDto();
        } else {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No se pudo actualizar el vale");
        }
    }


    //METODO PARA ACTUALIZAR EL ESTADO DE LA SOLICITUD
    @Override
    public SolicitudValeModDto actualizarEstadoSolicitud(UUID id, int estadoSolicitud) {
        SolicitudVale solicitudVale = this.solicitudValeRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro la solicitud"));
        if (solicitudVale != null) {
            solicitudVale.setEstado(estadoSolicitud);
            return solicitudValeRepository.save(solicitudVale).toSolicitudValeModDto();
        } else {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No se pudo actualizar la solicitud");
        }
    }

    @Override
    public SolicitudValeEstadoEntradaDto actualizarEstadoEntradaSolicitud(UUID id, int estadoSolicitud) {
        SolicitudVale solicitudVale = this.solicitudValeRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro la solicitud"));

        if (solicitudVale != null) {
            solicitudVale.setEstadoEntrada(estadoSolicitud);
            return solicitudValeRepository.save(solicitudVale).toSolicitudEstadoEntradadDto();
        } else {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No se pudo actualizar la solicitud");
        }
    }

    //METODO PARA ACTUALIZAR EL ESTADO DE LA SOLICITUD DEL VEHÍCULO
    @Override
    public SolicitudVehiculoModDto actualizarEstadoSolicitudVehiculo(UUID id, int estadoSolicitudVehiculo) {
        SolicitudVehiculo solicitudVehiculo = this.solicitudVehiculoRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro la solicitud del Vale"));

        if (solicitudVehiculo != null) {
            solicitudVehiculo.setEstado(estadoSolicitudVehiculo);
            return solicitudVehiculoRepository.save(solicitudVehiculo).totoSolicitudVehiculoModDto();
        } else {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No se pudo actualizar la solicitud del Vehículo");
        }
    }


    //METODO PARA LIQUIDAR LOS VALES
    @Override
    @Transactional
    public LiquidarValesDto liquidarVales(LiquidarValesDto data, String usuario, String empleado, String cargo) {
        System.out.println("Dto: " + data);
        LogValeDto logVale = new LogValeDto();

        LocalDateTime fechaActualLog = LocalDateTime.now();


        LogSolicitudVehiculo logSolicitudVehiculo = new LogSolicitudVehiculo();


        // 11 = vale consumido
        // 7 = Finalizado para las solicitudes y la asignación
        int estadoVale = 11;
        int estadoSolicitudes = 7;
        String movimientoVale = "";

        String actividad = "Misón finalizada";

        // Se Utiliza para buscar el ID de la Solicitud del Vale
        AsignacionVale asignacionVale = this.asignacionValeRepository.findById(data.getIdAsignacionVale()).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro la asignacion"));

        //Cambio el estado de las solicitudes, los vales y la asignación
        try {
            for (int i = 0; i < data.getValesLiquidar().size(); i++) {
                movimientoVale = asignacionVale.getSolicitudVale().getSolicitudVehiculo().getObjetivoMision() + " "
                        + asignacionVale.getSolicitudVale().getSolicitudVehiculo().getLugarMision();
                //Cambian el estado de los vales
                actualizarEstadoVale(data.getValesLiquidar().get(i), estadoVale);
                logVale.setEstadoVale(estadoVale);
                logVale.setFechaLogVale(fechaActualLog);
                logVale.setActividad("Vale consumido en la misión " + movimientoVale);
                logVale.setVale(data.getValesLiquidar().get(i));
                logVale.setUsuario(usuario);
                logVale(logVale);
            }
            //Cambian el estado de la asignación
            actualizarEstadoAsignacion(data.getIdAsignacionVale(), estadoSolicitudes);
            //Cambian el estado de la solicitud del vale
            actualizarEstadoSolicitud(asignacionVale.getSolicitudVale().getIdSolicitudVale(), estadoSolicitudes);
            //Cambian el estado de la solicitud del vehículo
            actualizarEstadoSolicitudVehiculo(asignacionVale.getSolicitudVale().getSolicitudVehiculo().getCodigoSolicitudVehiculo(), estadoSolicitudes);

            SolicitudVale solicitudVale = this.solicitudValeRepository.findById(asignacionVale.getSolicitudVale().getIdSolicitudVale()).orElseThrow(
                    () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontró la solicitud: " + asignacionVale.getSolicitudVale().getIdSolicitudVale())
            );

            logSolicitudVehiculo.setSoliVale(solicitudVale);
            logSolicitudVehiculo.setEstadoLogSolive(estadoSolicitudes);
            logSolicitudVehiculo.setFechaLogSoliVe(fechaActualLog);
            logSolicitudVehiculo.setActividad(actividad);
            logSolicitudVehiculo.setUsuario(empleado);
            logSolicitudVehiculo.setCargo(cargo);
            logSolicitudVehiculo.setSoliVe(solicitudVale.getSolicitudVehiculo());
            this.logSoliVeRepository.save(logSolicitudVehiculo);

        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No se pudo liquidar el vale");
        }
        return data;
    }

    //METODO PARA ACTUALIZAR EL ESTADO DE LA ASIGNACIÓN
    @Override
    public AsignacionValeModDto actualizarEstadoAsignacion(UUID id, int estadoAsignacion) {
        AsignacionVale asignacionVale = this.asignacionValeRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro la asignacion"));

        if (asignacionVale != null) {
            asignacionVale.setEstado(estadoAsignacion);
            return asignacionValeRepository.save(asignacionVale).toAsignacionValeModDto();
        } else {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No se pudo actualizar la solicitud del Vehículo");
        }
    }

    @Override
    public LogValeDto logVale(LogValeDto data) {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MMMM/dd");

        Vale vale = this.valeRepository.findById(data.getVale()).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro el vale"));
        LogVale logVale = new LogVale();

        logVale.setEstadoVale(data.getEstadoVale());
        logVale.setFechaLogVale(data.getFechaLogVale());
        logVale.setActividad(data.getActividad());
        logVale.setUsuario(data.getUsuario());
        logVale.setVale(vale);

        try {
            logValeRepository.save(logVale);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No se pudo realizar el log");
        }
        return data;
    }

    @Override
    public LogSolicitudValeDto logSolicitudVale(LogSolicitudValeDto data) {

        SolicitudVale solicitudVale = this.solicitudValeRepository.findById(data.getSoliVale()).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro la solicitud del vale"));

        LogSolicitudVehiculo logSolicitudVehiculo = new LogSolicitudVehiculo();

        logSolicitudVehiculo.setSoliVale(solicitudVale);
        logSolicitudVehiculo.setEstadoLogSolive(data.getEstadoLogSoli());
        logSolicitudVehiculo.setFechaLogSoliVe(data.getFechaLogSoli());
        logSolicitudVehiculo.setActividad(data.getActividad());
        logSolicitudVehiculo.setUsuario(data.getUsuario());

        try {
            logSoliVeRepository.save(logSolicitudVehiculo);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No se pudo realizar el log");
        }

        return data;
    }

    @Override
    public Page<SolicitudVale> listarSolicitudVale(Pageable pageable) {
        return solicitudValeRepository.findAll(pageable);
    }

    //METODO PARA VER LA CANTIDAD DE VALES DISPONIBLES
    @Override
    public CantidadValesDto cantidadVales() {
        CantidadValesDto cantidadValesDto = new CantidadValesDto();
        cantidadValesDto.setValesDisponibles(this.asignacionValeRepository.valesDisponibles());
        return cantidadValesDto;
    }

    //METODO PARA VER EL CÓDIGO DE LA SOLICITUD DEL VALE
    @Override
    public BuscarSolicitudValeDto codigoSolictudVale(UUID id) {
        BuscarSolicitudValeDto buscarSolicitudValeDto = new BuscarSolicitudValeDto();

        buscarSolicitudValeDto.setCodigoSolicitudVale(this.asignacionValeRepository.findByIDSolicitudVale(id));
        return buscarSolicitudValeDto;
    }

    //METODO PARA VER EL CÓDIGO DE LA ASIGNACIÓN
    @Override
    public BuscarAsignacionValeDto codigoAsignacionVale(UUID id) {
        BuscarAsignacionValeDto buscarAsignacionValeDto = new BuscarAsignacionValeDto();

        buscarAsignacionValeDto.setCodigoAsignacion(this.asignacionValeRepository.findByIdAsignacionVale(id));
        return buscarAsignacionValeDto;
    }

    //METODO PARA VER EL CÓDIGO DE LA SOLICITUD DEL VEHÍCULO
    @Override
    public BuscarSolicitudVehiculoDto codigoSolicitudVehiculo(UUID id) {
        BuscarSolicitudVehiculoDto buscarSolicitudVehiculoDto = new BuscarSolicitudVehiculoDto();

        buscarSolicitudVehiculoDto.setCodigoSolicitudVehiculo(this.asignacionValeRepository.findByIdSolicitudVehiculo(id));
        return buscarSolicitudVehiculoDto;
    }

    @Override
    public List<ISolicitudValeFiltradasDto> findSolicitudValeByEstado(int estado) {
        if (this.solicitudValeRepository.findSolicitudValeByEstado(estado).isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "no hay solicitudes de Vales");
        } else {
            return this.solicitudValeRepository.findSolicitudValeByEstado(estado);
        }
    }

    @Override
    public int findSoliciVByEstado(int estado) throws IOException {
            List<ISolicitudValeFiltradasDto> lista = this.solicitudValeRepository.findSolicitudValeByEstado(estado);
            return lista.size();
    }


    @Override
    public List<ISolicitudValeFiltradasDto> findSolicitudValeByCodigo(UUID codigo) throws IOException {
        if (this.solicitudValeRepository.findSolicitudValeByCodigo(codigo).isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "no hay solicitudes de Vales");
        } else {
            return this.solicitudValeRepository.findSolicitudValeByCodigo(codigo);
        }
    }

    @Transactional
    @Override
    public SolicitudValeAprobarDto actualizarSolicitudAprobar(SolicitudValeAprobarDto data, String usuario, String cargo){

        LocalDateTime fechaActualLog = LocalDateTime.now();
        System.out.println(data);
        String actividad = "";
        if (data.getEstadoSolicitudVale() == 4) {
            actividad = "Solicitud de vale aprobada ";
        } else if (data.getEstadoSolicitudVale() == 6) {
            actividad = "Solicitud de vale enviada a revisión ";
        } else if (data.getEstadoSolicitudVale() == 1) {
            actividad = "Solicitud de vale por aprobar enviada ";
        } else if (data.getEstadoSolicitudVale() == 5) {
            actividad = "Vales Asignados a Solicitud de vale ";
        }


        SolicitudVale solicitudVale = this.solicitudValeRepository.findById(data.getCodigoSolicitudVale()).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontró la solicitud: " + data.getCodigoSolicitudVale())
        );

        LogSolicitudVehiculo logSolicitudVehiculo = new LogSolicitudVehiculo();

        logSolicitudVehiculo.setSoliVale(solicitudVale);
        logSolicitudVehiculo.setEstadoLogSolive(data.getEstadoSolicitudVale());
        logSolicitudVehiculo.setFechaLogSoliVe(fechaActualLog);
        logSolicitudVehiculo.setActividad(actividad);
        logSolicitudVehiculo.setCargo(cargo);
        logSolicitudVehiculo.setUsuario(usuario);

        try {
            solicitudVale.setEstado(data.getEstadoSolicitudVale());
            solicitudVale.setCantidadVale(data.getCantidadVales());
            solicitudVale.setObservaciones(data.getObservaciones());
            this.solicitudValeRepository.save(solicitudVale).toSolicitudValeAprobarDto();
            this.logSoliVeRepository.save(logSolicitudVehiculo);

        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No se pudo modificar la solicitud para aprobar");
        }
        return data;
    }

    @Override
    public List<EmpleadosCorreosSolicitudesDto> correosFinanciero() {
        return asignacionValeRepository.correosFinanciero();
    }

    @Override
    public List<EmpleadosCorreosSolicitudesDto> correoById(UUID codigoEmpleado) throws IOException {
        return asignacionValeRepository.correoById(codigoEmpleado);
    }

}


