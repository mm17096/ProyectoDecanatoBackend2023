package com.ues.edu.apidecanatoce.dtos.entradasalidaDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.entities.cargos.Cargo;
import com.ues.edu.apidecanatoce.entities.entradaSalida.Entrada_Salidas;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.entradaSalida.EntradasalidaRepository;
import com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo.ISolicitudVehiculoRepository;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class EntradasalidaDto {
    private UUID id;

    @NotNull(message = "La fecha es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @NotNull(message = "La hora es obligatoria")
    private LocalTime hora;

    @NotBlank(message = "El campo combustible es obligatorio")
    private String combustible;

    @NotNull(message = "El campo kilometraje es obligatorio")
    @Min(value = 0, message = "La cantidad debe ser mayor o igual a 0")
    @Column(name = "kilometraje")
    private String kilometraje;

    private int estado;

    @NotNull(message = "El campo Misión es obligatorio")
    private UUID solicitudvehiculo;

    public Entrada_Salidas toEntityComplete(ISolicitudVehiculoRepository solicitudvehiculo) {
        SolicitudVehiculo solicitudbuscar = solicitudvehiculo.findById(this.solicitudvehiculo).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontro el objetivo de la Misión"));

        return Entrada_Salidas.builder().codigoEntradaSalida(this.id).fecha(this.fecha).hora(this.hora).combustible(this.combustible).kilometraje(this.kilometraje).estado(this.estado).solicitudvehiculo(solicitudbuscar).build();
    }
}
