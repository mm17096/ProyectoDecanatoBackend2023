package com.ues.edu.apidecanatoce.dtos.asignacionValesDto.vales;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.entities.compras.Vale;
import com.ues.edu.apidecanatoce.entities.logVale.LogVale;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.compras.IValeRepository;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LogValeDto {

    private UUID idLogVale;

    private Integer estadoVale;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "fecha_logvale", nullable = false)
    private LocalDateTime fechaLogVale;

    private String actividad;
    /*private String usuario;*/
    private UUID vale;

    private  String usuario;

    public LogVale toEntity(IValeRepository vale){
        Vale vales = vale.findById(this.vale).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontro cargo"));

        return LogVale.builder()
                .id(this.idLogVale)
                .estadoVale(this.estadoVale)
                .fechaLogVale(this.fechaLogVale)
                .actividad(this.actividad)
                .vale(vales)
                .usuario(this.usuario)
                .build();
    }

}
