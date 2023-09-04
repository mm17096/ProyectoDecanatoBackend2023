package com.ues.edu.apidecanatoce.entities.estados;

import com.ues.edu.apidecanatoce.dtos.estados.EstadosDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.core.annotation.Order;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="tb_estados")
@Order(2)
public class Estados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="codigo_estado")
    private Integer codigoEstado;

    @Column(name = "nombre_estado")
    private String nombreEstado;

    public EstadosDTO toDTO() {
        return EstadosDTO.builder().codigoEstado(this.codigoEstado).nombreEstado(this.nombreEstado).build();
    }

}
