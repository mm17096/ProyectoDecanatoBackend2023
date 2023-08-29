package com.ues.edu.apidecanatoce.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.ValeDto;
import com.ues.edu.apidecanatoce.entities.AsignacionVales.DetalleAsignacionVale;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="tb_vale")
public class Vale {
    @Id
    @Column(name = "id_vale")
    private String idVale;

    @Column(name ="codigo_vale")
    private long codigoVale;


    @Column(name = "estado")
    private int estado;

    @OneToMany(mappedBy = "vale")
    private Set<DetalleAsignacionVale> detalleAsignacionValeSet;

    public ValeDto toValeDto() {
        return ValeDto.builder()
                .idVale(this.idVale)
                .codigoVale(this.codigoVale)
                .build();
    }

}
