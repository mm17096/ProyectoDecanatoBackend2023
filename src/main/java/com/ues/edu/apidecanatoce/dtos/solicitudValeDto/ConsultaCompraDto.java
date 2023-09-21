package com.ues.edu.apidecanatoce.dtos.solicitudValeDto;

import java.time.LocalDate;
import java.util.UUID;

public interface ConsultaCompraDto {
    UUID getCodigocompra();
    Integer getCantidad();
    Integer getCodigofin();
    Integer getCodigoinicio();
    LocalDate getFechacompra();
    LocalDate getFechavencimientovale();
    float getPreciounitario();

}
