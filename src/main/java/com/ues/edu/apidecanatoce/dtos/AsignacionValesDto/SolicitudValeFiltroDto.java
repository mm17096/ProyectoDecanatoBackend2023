package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SolicitudValeFiltroDto {
    public UUID CodigoSolicitudVale;

    public Integer CantidadVales;

    public Integer EstadoSolicitud;

    public Integer EstadoEntradaSolicitudVale;

    public String observacionesSolicitudVale;

    public String ModeloVehiculo;

    public UUID CodigoSolicitudVehiculoS;

    public Integer CantidadPersonas;

    public String LugarMision;

    public String DireccionMision;

    public String ObjetivoMision;

    public String FechaSalida;

    public String FechaEntrada;

    public Integer EstadoSolicitudVehiculo;

    public String NombreSolicitante;

    public String NombreMotorista;

    public UUID CodigoEmpleado;

    public String CorreoEmpleado;
}
