package com.ues.edu.apidecanatoce.dtos.solicitudVehiculo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.LogSolicitudVehiculo;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.asignacionvale.ISolicitudValeRepository;
import com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo.ISolicitudVehiculoRepository;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogSoliVeDTO {

    private UUID idLogSoliVe;
    private Integer estadoLogSolive;
    private String estadoString;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fecha_logsolive", nullable = false)
    private LocalDateTime fechaLogSoliVe;

    private String actividad;

    private String usuario;

    private UUID soliVe;

    private UUID soliVale;

    public LogSolicitudVehiculo toEntity(ISolicitudVehiculoRepository soliVe,
                                         ISolicitudValeRepository soliVal) {
        SolicitudVehiculo solisVe = soliVe.findById(this.soliVe).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontro la solicitud de vehiculo"));

        SolicitudVale solValeB = null;
        if (this.soliVale != null){
            solValeB = soliVal.findById(this.soliVe).orElseThrow(
                    () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontró la solicitud de vale con ID: " + this.soliVe)
            );
        }

        return LogSolicitudVehiculo.builder()
                .idLogSoliVe(this.idLogSoliVe)
                .estadoLogSolive(this.estadoLogSolive)
                .fechaLogSoliVe(this.fechaLogSoliVe)
                .actividad(this.actividad)
                .soliVe(solisVe).build();
    }
}
