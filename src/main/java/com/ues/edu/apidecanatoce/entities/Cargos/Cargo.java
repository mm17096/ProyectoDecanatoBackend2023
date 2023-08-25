package com.ues.edu.apidecanatoce.entities.Cargos;

import com.ues.edu.apidecanatoce.dtos.CargosDto.CargosDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="tb_cargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name ="codigo_cargo")
    private UUID codigoCargo;

    @Column(name = "nombre_cargo")
    private String nombreCargo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private int estado;

    public CargosDto toDto(){
        return  CargosDto.builder().codigoCargo(this.codigoCargo).nombreCargo(this.nombreCargo)
                .descripcion(this.descripcion).estado(this.estado).build();
    }

}
