package com.ues.edu.apidecanatoce.dtos.solicitudValeDto;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ConsultaLogValeDto {
    UUID getIdlogvale();
    String getActividad();
    String getEstadovale();
    LocalDateTime getFechalogvale();
    UUID getIdvale();
    String getUsuario();
}
