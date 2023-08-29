package com.ues.edu.apidecanatoce.dtos;

import com.ues.edu.apidecanatoce.entities.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Builder
public class SolicitudVvDTO {
    private UUID idSolicitudVale;
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
    private int estado;
    private Empleado motorista; // ID del motorista
    //private List<Documentos> listDocumentos;


}
