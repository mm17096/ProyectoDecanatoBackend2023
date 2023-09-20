package com.ues.edu.apidecanatoce.entities.ajustesVale;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.dtos.ajusteVale.AjusteValeDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_ajuste_vale")

public class AjusteVale {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "codigo_ajuste")
    private UUID id;

    @Column(name = "descripcion", length = 750)
    private String descripcion;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "fecha_ajuste")
    private LocalDateTime fecha_ajuste;

    @Column(name = "estado")
    private int estado;
    public AjusteValeDto toDTO() {
        return AjusteValeDto.builder().id(this.id).descripcion(this.descripcion)
                .fecha_ajuste(this.fecha_ajuste)
                .estado(this.estado).build();
    }
}
