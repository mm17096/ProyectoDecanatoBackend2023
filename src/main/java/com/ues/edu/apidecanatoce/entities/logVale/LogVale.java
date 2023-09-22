package com.ues.edu.apidecanatoce.entities.logVale;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.LogValeDto;
import com.ues.edu.apidecanatoce.entities.compras.Vale;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_logvale")
@Builder
public class LogVale {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_logvale")
    private UUID id;

    @Column(name = "estado_vale")
    private int estadoVale;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "fecha_logvale", nullable = false)
    private LocalDateTime fechaLogVale;

    @Column(name = "actividad", length = 1000)
    private String actividad;

    @Column(name = "usuario")
    private String usuario;

    @ManyToOne
    @JoinColumn(name = "id_vale")
    private Vale vale;

    public LogValeDto toLogValeDto(){
        return LogValeDto.builder()
                .idLogVale(this.id)
                .estadoVale(this.estadoVale)
                .fechaLogVale(this.fechaLogVale)
                .actividad(this.actividad)
                .vale(this.vale.getId())
                .build();
    }
}
