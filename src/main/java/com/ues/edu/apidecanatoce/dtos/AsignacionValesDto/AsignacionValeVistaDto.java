package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto;

import com.ues.edu.apidecanatoce.entities.AsignacionVales.AsignacionVale;
import com.ues.edu.apidecanatoce.entities.AsignacionVales.DetalleAsignacionVale;
import com.ues.edu.apidecanatoce.entities.SolicitudVale;
import com.ues.edu.apidecanatoce.entities.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.entities.Vale;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
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

    List<ValeDto> vales;

    //private List<DetalleAsignacionVale> listaVales;

    public AsignacionValeOutDto toAsignacionVistaDto() {
        return AsignacionValeOutDto.builder().idAsignacionVale(this.idAsignacionVale)
                .fechaAsignacion(this.fechaAsignacion)
                .mision(this.mision.getSolicitudVehiculo().getObjetivoMision() + " " + this.mision.getSolicitudVehiculo().getLugarMision())
                .vales(this.vales)
                .build();
    }

}
