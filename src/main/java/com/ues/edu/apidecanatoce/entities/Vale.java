package com.ues.edu.apidecanatoce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_vale")
public class Vale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vale")
    private long idVale;

    @Column(name ="codigo_vale")
    private long codigoVale;

    @Column(name = "estado")
    private boolean estado;


}
