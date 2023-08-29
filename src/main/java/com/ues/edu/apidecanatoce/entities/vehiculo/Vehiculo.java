package com.ues.edu.apidecanatoce.entities.vehiculo;

import com.ues.edu.apidecanatoce.dtos.vehiculo.VehiculoDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="tb_vehiculo")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "codigo_vehiculo")
    private UUID codigoVehiculo;
    @Column(name = "placa")
    private String placa;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "marca")
    private String marca;
    @Column(name = "clase")
    private String clase;
    @Column(name = "color")
    private String color;
    @Column(name = "year")
    private int year;
    @Column(name = "fecha_tarjeta")
    private LocalDate fecha_tarjeta;
    @Column(name = "capacidad")
    private Integer capacidad;
    @Column(name = "capacidad_tanque")
    private String capacidadTanque;
    @Column(name = "estado")
    private int estado;
    @Column(name = "numero_chasis")
    private String n_chasis;
    @Column(name = "numero_motor")
    private String n_motor;
    @Column(name = "tipo_gas")
    private String tipo_gas;
    @Column(name = "nombrefoto")
    private String nombrefoto;
    @Column(name = "urlfoto")
    private String urlfoto;

    public VehiculoDto toDTO(){
        return VehiculoDto.builder().codigoVehiculo(codigoVehiculo).placa(placa).modelo(modelo)
                .marca(marca).clase(clase).color(color).year(year).fecha_tarjeta(fecha_tarjeta)
                .capacidad(capacidad).capacidadTanque(capacidadTanque).estado(estado).n_chasis(n_chasis)
                .n_motor(n_motor).tipo_gas(tipo_gas).nombrefoto(nombrefoto).urlfoto(urlfoto).build();
    }
}

