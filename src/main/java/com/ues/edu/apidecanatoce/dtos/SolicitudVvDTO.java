package com.ues.edu.apidecanatoce.dtos;

import com.ues.edu.apidecanatoce.entities.*;
import com.ues.edu.apidecanatoce.entities.vehiculo.Vehiculo;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class SolicitudVvDTO {
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
    //private List<Pasajeros> listaPasajeros;
    private Usuario solicitante; // usuario solicitante
    private String nombreJefeDepto;
    private LocalDate fechaEntrada;
    private String estado;
    private Empleado motorista; // ID del motorista
    //private List<Documentos> listDocumentos;

}
