package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.solicitudes;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


public interface EmpleadosCorreosSolicitudesDto {
    String getcodigoUsuario();
    String getRol();
    String getNombre();
    String getCorreo();
}
