package com.ues.edu.apidecanatoce.dtos.solicitudValeDto;

import java.time.LocalDate;
import java.util.UUID;

public interface ConsultaSoliValeIdDto {
    UUID getCodigodocumentos();
    String getComprobante();
    LocalDate getFecha();
    String getFoto();
    String getTipo();
    String getUrl();
    UUID getIdsolicitudvale();
}
