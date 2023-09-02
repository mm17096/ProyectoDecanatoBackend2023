package com.ues.edu.apidecanatoce.dtos.solicitudVehiculo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.dtos.vehiculo.VehiculoDto;
import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.DocumentoSoliCar;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.Pasajeros;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.entities.usuario.Usuario;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class SolicitudVehiculoPeticionDtO {
    private UUID codigoSolicitudVehiculo;

    @NotNull(message = "Fecha de realización de la solicitud es obligatoria")
    @FutureOrPresent(message = "La fecha es superior a la actual")
    @PastOrPresent(message = "La fecha es inferior a la actual")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaSolicitud;

    @PastOrPresent(message = "La fecha es inferior a la actual")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaSalida;

    @NotNull(message = "La unidad solicitante es obligataria")
    @Max(value = 50, message = "Maximo 50 caracteres para la unidad solcitante")
    private String unidadSolicitante;

    @NotNull(message = "El vehículo es obligaatorio")
    private VehiculoDto vehiculo;

    @NotNull(message = "El objetivo de misión es obligatorio")
    private String objetivoMision;

    @NotNull(message = "El lugar de la misión es obligatorio")
    private String lugarMision;

    @NotNull(message = "La dirección de la misión es obligatoria")
    private String direccion;

    @NotNull(message = "La hora de regreso es obligatoria")
    @DateTimeFormat(pattern = "HH:mm", iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime horaEntrada;

    @NotNull(message = "La hora de salida es obligatoria")
    @DateTimeFormat(pattern = "HH:mm", iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime horaSalida;

    @NotNull(message = "La cantidad de pasajeros es obligatoria")
    @Min(value = 1, message = "La cantidad de personas debe ser mayor o igual a 1")
    private int cantidadPersonas;

    private List<Pasajeros> listaPasajeros;

    @NotNull(message = "El responsable es obligatorio")
    private Usuario solicitante; // usuario solicitante

    @Max(value = 150, message = "El nombre del jefe de departamento que aprueba excede el límite de caracteres")
    private String nombreJefeDepto;

    @PastOrPresent(message = "La fecha es inferior a la actual")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaEntrada;

    private int estado;

    private String estadoString;

    private Empleado motorista; // ID del motorista

    private List<DocumentoSoliCar> listDocumentos;

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
