package com.ues.edu.apidecanatoce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_Mantenimiento")
public class Mantenimeinto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "codigo_mantenimiento")
    private Integer codigoMantenimiento;
    @Column(name = "foto")
    private String foto;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "monto")
    private String monto;
    @Column(name = "numero_factura")
    private String N_Factura;
}
