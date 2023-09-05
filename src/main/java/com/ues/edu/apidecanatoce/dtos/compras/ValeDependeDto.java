package com.ues.edu.apidecanatoce.dtos.compras;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.entities.compras.Compra;
import com.ues.edu.apidecanatoce.entities.compras.Vale;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@Builder

public class ValeDependeDto {

    private UUID id;

    @NotNull(message = "Codigo Vale es obligatorio")
    @Digits(integer = 19, fraction = 0, message = "Codigo Vale debe ser un número entero válido con máximo 19 dígitos")
    @Min(value = 0, message = "Codigo Vale debe ser mayor o igual a 0")
    private Long codigoVale;

    @NotNull(message = "El estado es obligatorio")
    @Digits(integer = 10, fraction = 0, message = "El estado debe ser un número válido con máximo 10 dígitos en total y 0 decimales")
    private int estado;

    @NotNull(message = "El valor es obligatorio")
    @Digits(integer = 10, fraction = 2, message = "El valor debe ser un número válido con máximo 10 dígitos en total y 2 decimales")
    private double valor;

    private Compra compra;

    @NotNull(message = "La fecha de vencimiento es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Debe ser una fecha superior o igual al presente")
    private LocalDate fecha_vencimiento;

    @NotNull(message = "Correlativo es obligatorio")
    @Digits(integer = 19, fraction = 0, message = "Correlativo debe ser un número entero válido con máximo 19 dígitos")
    @Min(value = 0, message = "Correlativo debe ser mayor o igual a 0")
    private Long correlativo;

    public Vale toEntitySaveDep() {
        return Vale.builder().id(this.id).codigoVale(this.codigoVale).estado(this.estado).valor(this.valor).
                compra(this.compra)
                .fecha_vencimiento(this.fecha_vencimiento).correlativo(this.correlativo).build();
    }



}
