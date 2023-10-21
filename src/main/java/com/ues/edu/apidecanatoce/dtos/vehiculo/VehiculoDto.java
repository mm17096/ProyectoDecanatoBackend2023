package com.ues.edu.apidecanatoce.dtos.vehiculo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.entities.vehiculo.Vehiculo;
import jakarta.validation.constraints.*;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
public class VehiculoDto {

    private UUID codigoVehiculo;

    @NotNull(message = "La placa es obligatoria")
    @Size(max = 8, message = "La placa excede los limites de caracteres")
    private String placa;

    @NotNull(message = "El modelo es obligario")
    private String modelo;

    @NotNull(message = "La marca es obligario")
    private String marca;

    @NotNull(message = "La clase es obligario")
    private String clase;

    @NotNull(message = "El color es obligario")
    private String color;

    private int year;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    //@FutureOrPresent(message = "Fecha vecimiento superior al presente")
    private LocalDate fecha_tarjeta;

    @NotNull(message = "La capacidad personas es obligario")
    private Integer capacidad;

    @NotNull(message = "La capacidad es obligario")
    private String capacidadTanque;

    private int estado;

    @NotNull(message = "El numero chasis es obligario")
    @Size(max = 17, message = "El numero chasis excede los limites de caracteres")
    private String n_chasis;

    @NotNull(message = "El numero motor es obligario")
    @Size(max = 10, message = "El numero motor excede los limites de caracteres")
    private String n_motor;

    @NotNull(message = "El tipo gas es obligario")
    private String tipo_gas;

    private String nombrefoto;

    private String urlfoto;

    public Vehiculo toEntity(){
        return Vehiculo.builder().codigoVehiculo(codigoVehiculo).placa(placa).modelo(modelo)
                .marca(marca).clase(clase).color(color).year(year).fecha_tarjeta(fecha_tarjeta)
                .capacidad(capacidad).capacidadTanque(capacidadTanque).estado(estado).n_chasis(n_chasis)
                .n_motor(n_motor).tipo_gas(tipo_gas).nombrefoto(nombrefoto).urlfoto(urlfoto).build();
    }
}
