package com.ues.edu.apidecanatoce.dtos.asignacionValesDto.solicitudes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.LogSolicitudVehiculo;
import com.ues.edu.apidecanatoce.repositorys.asignacionvale.ISolicitudValeRepository;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LogSolicitudValeDto {

    private UUID idLogSolicitudes;

    private Integer estadoLogSoli;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fecha_logsolive", nullable = false)
    private LocalDateTime fechaLogSoli;

    private String actividad;

    private String usuario;

    private UUID soliVale;

    public LogSolicitudVehiculo toEntitySoli(ISolicitudValeRepository soliVale){
        SolicitudVale solicitudVale = soliVale.findById(this.soliVale).orElseThrow(
                () -> new RuntimeException("No se encontro la solicitud de vale"));
        return LogSolicitudVehiculo.builder()
                .idLogSoliVe(this.idLogSolicitudes)
                .estadoLogSolive(this.estadoLogSoli)
                .fechaLogSoliVe(this.fechaLogSoli)
                .actividad(this.actividad)
                .usuario(this.usuario)
                .soliVale(solicitudVale)
                .build();
    }


}
