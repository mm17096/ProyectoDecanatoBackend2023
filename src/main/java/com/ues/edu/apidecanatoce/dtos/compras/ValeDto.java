package com.ues.edu.apidecanatoce.dtos.compras;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.entities.compras.Compra;
import com.ues.edu.apidecanatoce.entities.compras.Vale;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.compras.ICompraRepository;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@Builder

public class ValeDto {

    private UUID id;

    @NotNull(message = "Codigo Vale es obligatorio")
    @Digits(integer = 19, fraction = 0, message = "Codigo Vale debe ser un número entero válido con máximo 19 dígitos")
    @Min(value = 0, message = "Codigo Vale debe ser mayor o igual a 0")
    private Long codigoVale;

    @NotNull(message = "El estado es obligatorio")
    @Digits(integer = 10, fraction = 0, message = "El estado debe ser un número válido con máximo 10 dígitos en total y 0 decimales")
    @Min(value = 0, message = "El estado debe ser mayor o igual a 0")
    private int estado;

    @NotNull(message = "El valor es obligatorio")
    @Digits(integer = 10, fraction = 2, message = "El valor debe ser un número válido con máximo 10 dígitos en total y 2 decimales")
    @Min(value = 0, message = "El valor debe ser mayor o igual a 0")
    private double valor;

    @NotNull(message = "Compra es obligatoria")
    private UUID compra;

    @NotNull(message = "La fecha de vencimiento es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Debe ser una fecha superior o igual al presente")
    private LocalDate fecha_vencimiento;

    @NotNull(message = "Correlativo es obligatorio")
    @Digits(integer = 19, fraction = 0, message = "Correlativo debe ser un número entero válido con máximo 19 dígitos")
    @Min(value = 0, message = "Correlativo debe ser mayor o igual a 0")
    private Long correlativo;

    public Vale toEntityComplete(ICompraRepository compraRepository) {
        Compra comprabuscar = compraRepository.findById(this.compra).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro compra"));
        return Vale.builder().id(this.id).codigoVale(this.codigoVale).estado(this.estado).valor(this.valor).compra(comprabuscar)
                .fecha_vencimiento(this.fecha_vencimiento).correlativo(this.correlativo).build();
    }


}
