package com.ues.edu.apidecanatoce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_EntradaSalida")
public class Entrada_Salidas {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "codigo_entradasalida")
    private Integer codigoEntradaSalida;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "hora")
    private LocalTime hora;
    @Column(name = "combustible")
    private String combustible;
    @Column(name = "kilometraje")
    private String kilometraje;

}
