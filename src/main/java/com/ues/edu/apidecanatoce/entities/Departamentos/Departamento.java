package com.ues.edu.apidecanatoce.entities.Departamentos;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_departamento")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name ="codigo_depto")
    private UUID codigoDepto;

    @Column(name ="nombre")
    private String nombre;

    private int estado;

}
