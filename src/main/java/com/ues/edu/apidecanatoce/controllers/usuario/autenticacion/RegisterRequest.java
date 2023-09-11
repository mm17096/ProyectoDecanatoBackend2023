package com.ues.edu.apidecanatoce.controllers.usuario.autenticacion;

import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
     String nombre;
     String clave;
     UUID empleado;
}
