package com.ues.edu.apidecanatoce.dtos.solicitudVehiculo;

import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.DocumentoSoliCar;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.Pasajeros;
import com.ues.edu.apidecanatoce.entities.usuario.Usuario;
import com.ues.edu.apidecanatoce.entities.vehiculo.Vehiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudVehiculoDTOResponse {
    private UUID codigoSolicitudVehiculo;

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
    private List<DocumentoSoliCar> listDocumentos;
}
