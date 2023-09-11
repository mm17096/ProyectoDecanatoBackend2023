package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LogValeDto {

    private UUID idLogVale;

    private Integer estadoVale;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_logvale", nullable = false)
    private LocalDate fechaLogVale;

    private String actividad;

    private String usuario;

    private UUID vale;


}
