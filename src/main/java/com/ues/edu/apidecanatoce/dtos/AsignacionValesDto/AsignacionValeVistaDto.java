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
public class AsignacionValeVistaDto {

    private UUID idAsignacionVale;
    private LocalDate fechaAsignacion;
    private SolicitudVale mision;

    List<ValeModDto> vales;

    //private List<DetalleAsignacionVale> listaVales;

    public AsignacionValeOutDto toAsignacionVistaDto() {
        return AsignacionValeOutDto.builder().idAsignacionVale(this.idAsignacionVale)
                .fechaAsignacion(this.fechaAsignacion)
                .mision(this.mision.getSolicitudVehiculo().getObjetivoMision() + " " + this.mision.getSolicitudVehiculo().getLugarMision())
                .vales(this.vales)
                .build();
    }

}
