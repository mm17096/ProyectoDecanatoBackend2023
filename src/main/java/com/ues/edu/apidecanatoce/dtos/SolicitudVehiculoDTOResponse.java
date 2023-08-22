package com.ues.edu.apidecanatoce.dtos;

import com.ues.edu.apidecanatoce.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudVehiculoDTOResponse {
    private int codigoSolicitudVehiculo;
    private LocalDate fechaSolicitud;
    private LocalDate fechaSalida;
    private String unidadSolicitante;
    private Vehiculo vehiculo; // vehiculo
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
    private String estado;
    private Empleado motorista; // ID del motorista
    private List<Documentos> listDocumentos;
}
