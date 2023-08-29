package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto;

import com.ues.edu.apidecanatoce.entities.AsignacionVales.DetalleAsignacionVale;
import com.ues.edu.apidecanatoce.entities.SolicitudVale;
import com.ues.edu.apidecanatoce.entities.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.entities.Vale;
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
    List<ValeDto> vales;
    public AsignacionValeVistaDto toAsignacionValeVistaDto(){
        SolicitudVale solicitudVale = new SolicitudVale();
        return AsignacionValeVistaDto.builder()
                .idAsignacionVale(this.idAsignacionVale)
                .fechaAsignacion(this.fechaAsignacion)
                .vales(this.vales)
                .mision(solicitudVale).build();
    }

}
