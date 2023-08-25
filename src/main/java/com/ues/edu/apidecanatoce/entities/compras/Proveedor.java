package com.ues.edu.apidecanatoce.entities.compras;

import com.ues.edu.apidecanatoce.dtos.compras.ProveedorDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_proveedor")

public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "codigo_proveedor")
    private UUID id;

    @Column(name = "nombre", length= 200, unique = true)
    private String nombre;

    @Column(name = "telefono", length= 15, unique = true)
    private String telefono;

    @Column(name = "direccion", length= 750)
    private String direccion;

    @Column(name = "email", length= 100)
    private String email;

    public ProveedorDto toDTO() {
        return ProveedorDto.builder().id(this.id).nombre(this.nombre).telefono(this.telefono)
                .direccion(this.direccion).email(this.email).build();
    }



}
