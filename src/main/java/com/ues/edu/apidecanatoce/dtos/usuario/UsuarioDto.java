package com.ues.edu.apidecanatoce.dtos.usuario;

import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import com.ues.edu.apidecanatoce.entities.usuario.Usuario;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.empleado.IEmpleadoRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {

    private String codigoUsuario;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 35, message = "El nombre debe tener 35 caracteres como maximo")
    private String nombre;

    @NotBlank(message = "la clave es obligatorio")
    @Size(max = 50, message = "La clave debe tener 50 caracteres como maximo")
    private String clave;

    @NotBlank(message = "El estado nuevo es obligatorio")
    private boolean nuevo;

    private UUID empleado;

    public Usuario toEntityComplete(IEmpleadoRepository empleadoRepository) {
        Empleado empleadobuscar = empleadoRepository.findById(this.empleado).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontro empleado"));

        return Usuario.builder().codigoUsuario(this.codigoUsuario).nombre(this.nombre).clave(this.clave)
                .nuevo(this.nuevo).empleado(empleadobuscar).build();
    }
}
