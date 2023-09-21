package com.ues.edu.apidecanatoce.dtos.cargosDto;

import com.ues.edu.apidecanatoce.entities.cargos.Cargo;
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

    private UUID id;

    @NotBlank(message = "La nombre del cargo es obligatorio")
    @Size(min = 2, max = 750, message = "El nombre debe tener mas 2 caracteres")
     private String nombreCargo;

    @NotBlank(message = "La descripcion del cargo es obligatorio")
    @Size(min = 2, max = 750, message = "La descripción debe tener mas 2")
    private String descripcion;

    private int estado;

    public Cargo toEntityComplete(){
        return Cargo.builder().id(this.id).nombreCargo(this.nombreCargo)
                .descripcion(this.descripcion).estado(this.estado).build();
    }

}
