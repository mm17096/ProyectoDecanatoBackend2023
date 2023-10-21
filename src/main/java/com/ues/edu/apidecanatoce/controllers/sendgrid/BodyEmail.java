package com.ues.edu.apidecanatoce.controllers.sendgrid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BodyEmail {
    String asunto;
    String receptor;
    String titulo;
    String email;
    String mensaje;
    String centro;
    String codigo;
    String abajo;
}
