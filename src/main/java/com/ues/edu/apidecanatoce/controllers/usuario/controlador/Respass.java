package com.ues.edu.apidecanatoce.controllers.usuario.controlador;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Respass {
    String correo;
    String dui;
    String codigo;
}
