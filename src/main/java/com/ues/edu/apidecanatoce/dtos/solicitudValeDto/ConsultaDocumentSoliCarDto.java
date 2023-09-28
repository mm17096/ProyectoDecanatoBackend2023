package com.ues.edu.apidecanatoce.dtos.solicitudValeDto;

import java.time.LocalDate;
import java.util.UUID;

public interface ConsultaDocumentSoliCarDto {
    UUID getCodigodocumento();
    LocalDate getFecha();
    String getNombredocment();
    String getUrldocument();
    UUID getCodigosolicitudvehiculo();
}
