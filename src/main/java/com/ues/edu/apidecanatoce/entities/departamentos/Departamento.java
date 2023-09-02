package com.ues.edu.apidecanatoce.entities.departamentos;


import com.ues.edu.apidecanatoce.dtos.departamentoDto.DepartamentoDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_departamento")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name ="codigo_depto")
    private UUID codigoDepto;

    @Column(name ="nombre", unique = true)
    private String nombre;

    @Column(name ="estado")
    private int estado;

     public DepartamentoDto toDto(){
        return  DepartamentoDto.builder().codigoDepto(this.codigoDepto).nombre(this.nombre).estado(this.estado).build();
    }

}
