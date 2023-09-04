package com.ues.edu.apidecanatoce.dtos.compras;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.entities.compras.Compra;
import com.ues.edu.apidecanatoce.entities.compras.Proveedor;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.compras.IProveedorRepository;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Builder

public class CompraInsertarDto {
    private UUID id;

    @Size(max = 100, message = "La factura debe tener entre 1 y 100 caracteres")
    private String factura;

    @NotNull(message = "Proveerdor es obligatorio")
    private UUID proveedor;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 2, max = 750, message = "La descripción debe tener entre 2 y 750 caracteres")
    private String descripcion;

    @NotNull(message = "Cod inicio es obligatorio")
    @Min(value = 0, message = "Cod inicio debe ser mayor o igual a 0")
    private int cod_inicio;

    @NotNull(message = "Cod fin es obligatorio")
    @Min(value = 0, message = "Cod fin debe ser mayor o igual a 0")
    private int cod_fin;

    @NotNull(message = "La fecha de compra es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fecha_compra;

    @NotNull(message = "La fecha de vencimiento es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Fecha de vencimiento debe ser una fecha superior o igual al presente")
    private LocalDate fecha_vencimiento;

    @NotNull(message = "El precio unitario es obligatorio")
    @Digits(integer = 10, fraction = 2, message = "El precio unitario debe ser un número válido con máximo 10 dígitos en total y 2 decimales")
    @Min(value = 0, message = "El precio unitario debe ser mayor o igual a 0")
    private double precio_unitario;

    public Compra toEntityComplete(IProveedorRepository proveedorRepository) {
        Proveedor proveedorbuscar = proveedorRepository.findById(this.proveedor).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro proveedor"));
        return Compra.builder().id(this.id).factura(this.factura).proveedor(proveedorbuscar).descripcion(this.descripcion)
                .cod_inicio(this.cod_inicio).cod_fin(this.cod_fin).fecha_compra(this.fecha_compra).fecha_vencimiento(this.fecha_vencimiento).precio_unitario(this.precio_unitario).build();
    }

}
