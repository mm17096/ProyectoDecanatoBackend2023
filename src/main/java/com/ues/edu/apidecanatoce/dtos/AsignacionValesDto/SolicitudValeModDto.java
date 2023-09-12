package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto;

import com.ues.edu.apidecanatoce.entities.SolicitudVale;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class SolicitudValeModDto {

    private UUID idSolicitudVale;
    private int estadoSolicutudVale;

}
