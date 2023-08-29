package com.ues.edu.apidecanatoce.dtos.compras;

import com.ues.edu.apidecanatoce.entities.compras.Proveedor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@Builder

public class ProveedorDto {

    private UUID id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 200, message = "El nombre debe tener entre 2 y 200 caracteres")
    private String nombre;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]{8,15}$", message = "El teléfono debe contener solo dígitos y 8 caracteres")
    private String telefono;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(min = 2, max = 750, message = "La dirección debe tener entre 2 y 750 caracteres")
    private String direccion;

    @NotBlank(message = "El email es obligatorio")
    @Size(min = 2, max = 100, message = "El email debe tener entre 2 y 100 caracteres")
    @Email
    private String email;

    public Proveedor toEntityComplete() {
        return Proveedor.builder().id(this.id).nombre(this.nombre).telefono(this.telefono).direccion(this.direccion).email(this.email).build();
    }

}
