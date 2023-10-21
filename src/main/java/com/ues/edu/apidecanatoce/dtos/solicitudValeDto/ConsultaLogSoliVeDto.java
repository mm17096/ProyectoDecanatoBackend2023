package com.ues.edu.apidecanatoce.dtos.solicitudValeDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public interface ConsultaLogSoliVeDto {
    String getActividad();
    String getEstadosolive();
    LocalDateTime getFechalogsolive();
    String getUsuario();
    String getCargo();

}
