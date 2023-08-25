package com.ues.edu.apidecanatoce.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_departamento")
public class Departamento {

    @Id
    @Column(name ="codigo_depto")
    private UUID codigoDepto;

    private String nombre;

    private int estado;

}
