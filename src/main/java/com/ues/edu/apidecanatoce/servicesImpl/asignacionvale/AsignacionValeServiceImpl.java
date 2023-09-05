package com.ues.edu.apidecanatoce.servicesImpl.asignacionvale;

import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.*;
import com.ues.edu.apidecanatoce.entities.AsignacionVales.AsignacionVale;
import com.ues.edu.apidecanatoce.entities.SolicitudVale;
import com.ues.edu.apidecanatoce.entities.compras.Vale;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.asignacionvale.IAsignacionValeRepository;
import com.ues.edu.apidecanatoce.repositorys.asignacionvale.IDetalleAsignacionRepository;
import com.ues.edu.apidecanatoce.repositorys.asignacionvale.ISolicitudValeRepository;
import com.ues.edu.apidecanatoce.repositorys.compras.IValeRepository;
import com.ues.edu.apidecanatoce.services.asignacionvale.IAsignacionValeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AsignacionValeServiceImpl implements IAsignacionValeService {

    private final IAsignacionValeRepository asignacionValeRepository;

    private final IDetalleAsignacionRepository detalleAsignacionRepository;

    private final ISolicitudValeRepository solicitudValeRepository;

    private final IValeRepository valeRepository;

    @Override
    public AsignacionValeInDto registrar(AsignacionValeInDto data) {


        // 5 = Asignado para los vales
        //7 = Finalizado para la solicitud
        //8 = Activo para la asignación
        int estadoVales = 5;
        int estadoSolicitud = 7;
        int estadoAsignacion = 8;


        //Para guardar en la tabla detalle
        DetalleAsignacionInDto detalleAsignacionInDto = new DetalleAsignacionInDto();

        List<UUID> valesAsignar = new ArrayList<>();
        int cantidadVales = data.getCantidadVales();


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

                try {
                    //Cambiar el estado del Vale
                    actualizarEstadoVale(valesAsignar.get(i), estadoVales);
                    //cambiar el estado de la solicitud del vale y del vehículo
                    actualizarEstadoSolicitud(data.getSolicitudVale(), estadoSolicitud);
                    // Guardar en la tabla detalle_asignación los id de los vales junto con la ültima asignación reistrada
                    detalleAsignacionRepository.save(detalleAsignacionInDto.toDto(asignacionValeRepository, valeRepository)).toDto();
                } catch (Exception e) {
                    throw new CustomException(HttpStatus.BAD_REQUEST, "No se pudo guardar el detalle");
                }
            }
            System.out.println("El detalle: " + detalleAsignacionInDto);


            // Retornar el mensaje que toso se guardó
            return data;
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No se pudo guardar la asignación");
        }

    }

    @Override
    public AsignacionValeDto leerPorId(UUID id) {
        return null;
    }

    @Override
    public Page<AsignacionValeDto> listar(Pageable pageable) {
        return null;
    }

    @Override
    public AsignacionValeDto actualizar(UUID id, AsignacionValeDto data) {
        return null;
    }

    @Override
    public AsignacionValeDto eliminar(UUID id) {
        return null;
    }

    @Override
    public AsignacionValeOutDto verAsignacionesById(UUID id) {

        AsignacionVale asignacionVale = this.asignacionValeRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro la asignacion"));
        return asignacionVale.toDto(asignacionValeRepository, detalleAsignacionRepository, id);
    }

    @Override
    public DetalleAsignacionDto verDetalleById(UUID id) {
        return null;
    }

    @Override
    public List<IValeAsignarDto> lisIValeAsignarDtos(int cantidadVales) throws IOException {
        return this.asignacionValeRepository.listarValesAsignar(cantidadVales);
    }

    @Override
    public ValeModDto actualizarEstadoVale(UUID id, int estadoVale) {
        Vale vale = this.valeRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro el vale"));
        if (vale != null) {
            vale.setEstado(estadoVale);
            return valeRepository.save(vale).toValeDto();
        } else {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No se pudo actualizar el vale");
        }
    }

    @Override
    public SolicitudValeModDto actualizarEstadoSolicitud(UUID id, int estadoSolicitud) {
        SolicitudVale solicitudVale = this.solicitudValeRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro la solicitud"));

        if (solicitudVale != null) {
            solicitudVale.setEstadoEntrada(estadoSolicitud);
            return solicitudValeRepository.save(solicitudVale).toSolicitudValeModDto();
        } else {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No se pudo actualizar la solicitud");
        }
    }
}


