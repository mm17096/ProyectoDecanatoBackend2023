package com.ues.edu.apidecanatoce.dtos.solicitudVehiculo;

import com.ues.edu.apidecanatoce.dtos.vehiculo.VehiculoDto;
import com.ues.edu.apidecanatoce.entities.*;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.entities.vehiculo.Vehiculo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class SolicitudVehiculoPeticionDtO {
    private UUID codigoSolicitudVehiculo;

    private LocalDate fechaSolicitud;
    private LocalDate fechaSalida;
    private String unidadSolicitante;


    private VehiculoDto vehiculo; // vehiculo cambiar a dto cuando lo hagan

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
    private String estadoString;

    private Empleado motorista; // ID del motorista

    private List<Documentos> listDocumentos;

    public SolicitudVehiculo toEntitySave() {

        //this.vehiculo.toEntityComlete
        return SolicitudVehiculo.builder().codigoSolicitudVehiculo(this.codigoSolicitudVehiculo)
                .fechaSolicitud(this.fechaSolicitud).fechaSalida(this.fechaSalida)
                .unidadSolicitante(this.unidadSolicitante).vehiculo(this.vehiculo.toEntity()).objetivoMision(this.objetivoMision)
                .lugarMision(this.lugarMision).direccion(this.direccion).horaEntrada(this.horaEntrada)
                .horaSalida(this.horaSalida).cantidadPersonas(this.cantidadPersonas).listaPasajeros(this.listaPasajeros)
                .usuario(this.solicitante).jefeDepto(this.nombreJefeDepto).fechaEntrada(this.fechaEntrada)
                .estado(this.estado).motorista(this.motorista).listDocumentos(this.listDocumentos).build();
    }
}
