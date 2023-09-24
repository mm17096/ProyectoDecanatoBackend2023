package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.detalles;

import com.ues.edu.apidecanatoce.entities.AsignacionVales.AsignacionVale;
import com.ues.edu.apidecanatoce.entities.AsignacionVales.DetalleAsignacionVale;
import com.ues.edu.apidecanatoce.entities.compras.Vale;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.asignacionvale.IAsignacionValeRepository;
import com.ues.edu.apidecanatoce.repositorys.compras.IValeRepository;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class DetalleAsignacionInDto {
    private UUID idDetalleAsignacionVale;
    private UUID codigoAsignacionVale;
    private UUID idVale;

    public DetalleAsignacionVale toDto(IAsignacionValeRepository asignacion, IValeRepository vale){
        AsignacionVale buscaAsignacion = asignacion.findById(this.codigoAsignacionVale).orElseThrow(
                () ->  new CustomException(HttpStatus.NOT_FOUND, "No se encontró la asignación: " + this.codigoAsignacionVale)
        );
        Vale buscaVale = vale.findById(this.idVale).orElseThrow(
                () ->  new CustomException(HttpStatus.NOT_FOUND, "No se encontró el vale: " + this.idVale)
        );
        return DetalleAsignacionVale.builder()
                .idDetalleAsignacionVale(this.idDetalleAsignacionVale)
                .asignacionVale(buscaAsignacion)
                .vale(buscaVale)
                .build();
    }
}
