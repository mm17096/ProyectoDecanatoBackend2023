package com.ues.edu.apidecanatoce.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudVehiculoDTO {
    private LocalDate fechaSolicitud;
    private String objetivoMision;
    private String lugarMision;
    private LocalDate fechaSalida;
    private LocalDate fechaEntrada;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private int cantidadPersonas;
    private int estado;
    private int codigoUsuario; // ID del usuario
    private String codigoMotorista; // ID del motorista
    private int codigoVehiculo; // ID del vehiculo
}
