package com.ues.edu.apidecanatoce.dtos.solicitudVehiculo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudVehiculoDTORequest {
    private LocalDate fechaSolicitud;
    private String objetivoMision;
    private String lugarMision;
    private LocalDate fechaSalida;
    private LocalDate fechaEntrada;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private int cantidadPersonas;
    private int estado;
    private UUID codigoUsuario; // ID del usuario
    private String codigoMotorista; // ID del motorista
    private UUID codigoVehiculo; // ID del vehiculo
}