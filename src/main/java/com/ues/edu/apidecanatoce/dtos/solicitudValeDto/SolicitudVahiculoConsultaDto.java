package com.ues.edu.apidecanatoce.dtos.solicitudValeDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.dtos.vehiculo.VehiculoDto;
import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.DocumentoSoliCar;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.Pasajeros;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.entities.usuario.Usuario;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Builder
public class SolicitudVahiculoConsultaDto {

    private UUID codigoSolicitudVehiculo;

    private LocalDate fechaSolicitud;

    private LocalDate fechaSalida;

    private String unidadSolicitante;

    private VehiculoDto vehiculo;

    private String objetivoMision;

    private String lugarMision;

    private String direccion;

    private LocalTime horaEntrada;

    private LocalTime horaSalida;

    private int cantidadPersonas;

    private Usuario solicitante; // usuario solicitante

    private String nombreJefeDepto;

    private LocalDate fechaEntrada;

    private int estado;
    private String estadoString;

    private Empleado motorista; // ID del motorista


    public SolicitudVehiculo toEntitySave() {

        //this.vehiculo.toEntityComlete
        return SolicitudVehiculo.builder().codigoSolicitudVehiculo(this.codigoSolicitudVehiculo)
                .fechaSolicitud(this.fechaSolicitud).fechaSalida(this.fechaSalida)
                .unidadSolicitante(this.unidadSolicitante).vehiculo(this.vehiculo.toEntity()).objetivoMision(this.objetivoMision)
                .lugarMision(this.lugarMision).direccion(this.direccion).horaEntrada(this.horaEntrada)
                .horaSalida(this.horaSalida).cantidadPersonas(this.cantidadPersonas)
                .usuario(this.solicitante).jefeDepto(this.nombreJefeDepto).fechaEntrada(this.fechaEntrada)
                .estado(this.estado).motorista(this.motorista).build();
    }
}
