package com.ues.edu.apidecanatoce.dtos.solicitudValeDto;

import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.LogValeDto;
import com.ues.edu.apidecanatoce.entities.AsignacionVales.AsignacionVale;
import com.ues.edu.apidecanatoce.entities.compras.Vale;
import com.ues.edu.apidecanatoce.entities.logVale.LogVale;
import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Builder
public class ConsultaValeDto {
    private UUID idDetalleAsignacionVale;
    private UUID asignacionVale;
    private UUID valeid;
    private Vale vale;
    private Integer estado;
    private LocalDate fecha;
    private SolicitudVale solicitudVale;
   // private List<LogVale> logVale;
}
