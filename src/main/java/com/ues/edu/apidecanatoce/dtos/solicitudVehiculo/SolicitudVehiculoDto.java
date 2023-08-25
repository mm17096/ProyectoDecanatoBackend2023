package com.ues.edu.apidecanatoce.dtos.solicitudVehiculo;

import com.ues.edu.apidecanatoce.entities.*;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.IEmpleadoRepository;
import com.ues.edu.apidecanatoce.repositorys.IVehiculoRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class SolicitudVehiculoDto {

    private UUID codigoSolicitudVehiculo;

    private LocalDate fechaSolicitud;
    private LocalDate fechaSalida;
    private String unidadSolicitante;

    private Vehiculo vehiculo; // vehiculo cambiar a int cuando este

    private String objetivoMision;
    private String lugarMision;
    private String direccion;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private int cantidadPersonas;

    private List<Pasajeros> listaPasajeros;

    private Usuario solicitante; // usuario solicitante

    private String nombreJefeDepto;
    private LocalDate fechaEntrada;
    private int estado;

    private Empleado motorista; // ID del motorista cambiar a string o uid cuando este dto

    private List<Documentos> listDocumentos;

    public SolicitudVehiculo toEntityComplete(IVehiculoRepository vehiculoRepository,
                                              IEmpleadoRepository empleadoRepository){
        // metodo para buscar el vehicul si existe
        Vehiculo vehiculoBuscar = vehiculoRepository.findById(this.vehiculo.getCodigoVehiculo()).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontró el vehículo"));

        Empleado motoristaBuscar = empleadoRepository.findById(this.motorista.getDui()).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontró el motorista"));

        return SolicitudVehiculo.builder().codigoSolicitudVehiculo(this.codigoSolicitudVehiculo)
                .fechaSolicitud(this.fechaSolicitud).fechaSalida(this.fechaSalida)
                .unidadSolicitante(this.unidadSolicitante).vehiculo(vehiculoBuscar).objetivoMision(this.objetivoMision)
                .lugarMision(this.lugarMision).direccion(this.direccion).horaEntrada(this.horaEntrada)
                .horaSalida(this.horaSalida).cantidadPersonas(this.cantidadPersonas).listaPasajeros(this.listaPasajeros)
                .usuario(this.solicitante).jefeDepto(this.nombreJefeDepto).fechaEntrada(this.fechaEntrada)
                .estado(this.estado).motorista(motoristaBuscar).listDocumentos(this.listDocumentos).build();
    }
}
