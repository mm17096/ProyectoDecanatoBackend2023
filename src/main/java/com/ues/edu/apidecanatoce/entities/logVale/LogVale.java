package com.ues.edu.apidecanatoce.entities.logVale;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.LogValeDto;
import com.ues.edu.apidecanatoce.entities.compras.Vale;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_logvale", nullable = false)
    private LocalDate fechaLogVale;

    @Column(name = "actividad", length = 750)
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
