package com.ues.edu.apidecanatoce.dtos.solicitudValeDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public interface ConsultaIdValeDto {
    UUID getIdvale();
    Long getCorrelativo();
    int getEstado();
    LocalDate getFechavencimiento();
    double getValor();
    UUID getCodigocompra();
}
