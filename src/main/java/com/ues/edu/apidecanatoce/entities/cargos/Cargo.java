package com.ues.edu.apidecanatoce.entities.cargos;

import com.ues.edu.apidecanatoce.dtos.cargosDto.CargosDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name="tb_cargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name ="id")
    private UUID id;

    @Column(name = "nombre_cargo", unique = true)
    private String nombreCargo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private int estado;

    public CargosDto toDto(){
        return  CargosDto.builder().id(this.id).nombreCargo(this.nombreCargo)
                .descripcion(this.descripcion).estado(this.estado).build();
    }

}
