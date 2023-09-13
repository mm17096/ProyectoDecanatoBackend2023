package com.ues.edu.apidecanatoce.dtos.entradasalidaDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoDto;
import com.ues.edu.apidecanatoce.entities.compras.Compra;
import com.ues.edu.apidecanatoce.entities.entradaSalida.Entrada_Salidas;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
@Getter
@Setter
@Builder
public class EntradasalidaPeticionDto {
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
    private SolicitudVehiculoDto solicitudvehiculo;

    public Entrada_Salidas toEntitySave() {
        return Entrada_Salidas.builder().codigoEntradaSalida(this.id).fecha(this.fecha).hora(this.hora).combustible(this.combustible).kilometraje(this.kilometraje).estado(this.estado).solicitudvehiculo(this.solicitudvehiculo.toEntityComplete2()).build();
    }
}
