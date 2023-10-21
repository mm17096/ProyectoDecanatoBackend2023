package com.ues.edu.apidecanatoce.dtos.solicitudValeDto;

import java.time.LocalDate;
import java.util.UUID;

public interface ConsultaCantidadValesDelAlDto {
    Integer getCantidadvale();
    Integer getCorrelativo();
    Integer getEstadovale();
    LocalDate getFechavencimiento();
    Integer getValor();
    UUID getValeid();
}
