package com.ues.edu.apidecanatoce.dtos.ajusteVale;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.entities.ajustesVale.AjusteVale;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Builder

public class AjusteValeDto {

    private UUID id;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 2, max = 750, message = "La descripción debe tener entre 2 y 750 caracteres")
    private String descripcion;

    @NotNull(message = "La fecha del ajuste es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fecha_ajuste;

    @NotNull(message = "El estado es obligatorio")
    @Min(value = 0, message = "El estado debe ser mayor o igual a 0")
    private int estado;

    public AjusteVale toEntityComplete() {
        return AjusteVale.builder().id(this.id).descripcion(this.descripcion).fecha_ajuste(this.fecha_ajuste).estado(this.estado).build();
    }

}
