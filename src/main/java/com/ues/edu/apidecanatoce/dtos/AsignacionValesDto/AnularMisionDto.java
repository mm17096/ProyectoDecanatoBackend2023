package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class AnularMisionDto {

    private UUID cosdigoAsignacion;
    private List<UUID> valesAsignacion;
}
