package com.ues.edu.apidecanatoce.dtos.CargosDto;

import com.ues.edu.apidecanatoce.entities.Cargos.Cargo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class CargosDto {

    private UUID codigoCargo;

    @NotBlank(message = "La nombre del cargo es obligatorio")
    @Size(min = 2, max = 750, message = "El nombre debe tener mas 2 caracteres")
    private String nombreCargo;

    @NotBlank(message = "La descripcion del cargo es obligatorio")
    @Size(min = 2, max = 750, message = "La descripción debe tener mas 2")
    private String descripcion;

    private int estado;

    public Cargo toEntityComplete(){
        return Cargo.builder().codigoCargo(this.codigoCargo).nombreCargo(this.nombreCargo)
                .descripcion(this.descripcion).estado(this.estado).build();
    }

}
