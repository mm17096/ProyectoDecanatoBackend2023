package com.ues.edu.apidecanatoce.entities.solicitudVehiculo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.solicitudes.LogSolicitudValeDto;
import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.LogSoliVeDTO;
import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_log_solicitud_vehiculo")
@Builder
public class LogSolicitudVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "codigo_logsolive")
    private UUID idLogSoliVe;

    @Column(name = "estado_solive", nullable = false)
    private int estadoLogSolive;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_logsolive", nullable = false)
    private LocalDateTime fechaLogSoliVe;

    @Column(name = "actividad", length = 750)
    private String actividad;

    @Column(name = "usuario", nullable = false)
    private String usuario;

    @ManyToOne
    @JoinColumn(name = "codigo_solive")
    private SolicitudVehiculo soliVe;

    @ManyToOne
    @JoinColumn(name = "codigo_solivale")
    private SolicitudVale soliVale;

    public LogSoliVeDTO toLogSoliVeDTO(){
        return LogSoliVeDTO.builder()
                .idLogSoliVe(this.idLogSoliVe)
                .estadoLogSolive(this.estadoLogSolive)
                .fechaLogSoliVe(this.fechaLogSoliVe)
                .actividad(this.actividad)
                .soliVe(this.soliVe.getCodigoSolicitudVehiculo()).build();
    }

    public LogSolicitudValeDto toDtoSoliVale(){
        return LogSolicitudValeDto.builder()
                .idLogSolicitudes(this.idLogSoliVe)
                .estadoLogSoli(this.estadoLogSolive)
                .fechaLogSoli(this.fechaLogSoliVe)
                .actividad(this.actividad)
                .usuario(this.usuario)
                .soliVale(this.soliVale.getIdSolicitudVale()).build();
    }

}
