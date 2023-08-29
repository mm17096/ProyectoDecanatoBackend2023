package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto;

import com.ues.edu.apidecanatoce.entities.AsignacionVales.DetalleAsignacionVale;
import com.ues.edu.apidecanatoce.entities.Vale;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class DetalleAsignacionDto {
    private UUID idDetalleAsignacionVale;
    private UUID codigoAsignacionVale;
    private Vale Vale;

    public DetalleAsignacionDto toDTODEntity() {
        return DetalleAsignacionDto.builder().idDetalleAsignacionVale(this.idDetalleAsignacionVale)
                .codigoAsignacionVale(this.codigoAsignacionVale)
                .Vale(this.Vale)
                .build();
    }
}
