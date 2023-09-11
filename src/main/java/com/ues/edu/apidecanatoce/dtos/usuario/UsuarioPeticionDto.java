package com.ues.edu.apidecanatoce.dtos.usuario;

import com.ues.edu.apidecanatoce.dtos.empleados.EmpleadoDto;
import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import com.ues.edu.apidecanatoce.entities.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioPeticionDto {

    private String codigoUsuario;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 35, message = "El nombre debe tener 35 caracteres como maximo")
    private String nombre;

    @NotBlank(message = "la clave es obligatorio")
    @Size(max = 50, message = "La clave debe tener 50 caracteres como maximo")
    private String clave;

    @NotBlank(message = "El estado nuevo es obligatorio")
    private boolean nuevo;

    private Empleado empleado;

    public Usuario toEntitySave() {
        return Usuario.builder().codigoUsuario(this.codigoUsuario).nombre(this.nombre).clave(this.clave)
                .empleado(this.empleado).build();
    }
}
