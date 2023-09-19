package com.ues.edu.apidecanatoce.dtos.solicitudValeDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


public interface ConsultaValeGDto {
    UUID getIddetalleasignacionvale();
    Integer getEstadoav();
    LocalDate getFecha();
    Integer getCantidadvale();
    Integer getEstadosv();
    Integer getEstadoentrada();
    Integer getCorrelativo();
    Integer getEstadovale();
    LocalDate getFechavencimiento();
    double getValor();

}
