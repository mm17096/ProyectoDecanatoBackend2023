package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto;

import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AsignacionValeOutDto {
    private UUID idAsignacionVale;
    private LocalDate fechaAsignacion;
    private String mision;
    List<ValeModDto> vales;
    public AsignacionValeVistaDto toAsignacionValeVistaDto(){
        SolicitudVale solicitudVale = new SolicitudVale();
        return AsignacionValeVistaDto.builder()
                .idAsignacionVale(this.idAsignacionVale)
                .fechaAsignacion(this.fechaAsignacion)
                .vales(this.vales)
                .mision(solicitudVale).build();
    }

}
