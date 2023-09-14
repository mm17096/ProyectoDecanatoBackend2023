package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.entities.compras.Vale;
import com.ues.edu.apidecanatoce.entities.logVale.LogVale;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.compras.IValeRepository;
import com.ues.edu.apidecanatoce.repositorys.logVale.ILogValeRepository;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

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
    /*private String usuario;*/
    private UUID vale;

    public LogVale toEntity(IValeRepository vale){
        Vale vales = vale.findById(this.vale).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontro cargo"));

        return LogVale.builder()
                .id(this.idLogVale)
                .estadoVale(this.estadoVale)
                .fechaLogVale(this.fechaLogVale)
                .actividad(this.actividad)
                .vale(vales)
                .build();
    }

}
