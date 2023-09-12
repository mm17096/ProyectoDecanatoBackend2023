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

    @Column(name = "encargado", length= 200)
    private String encargado;

    @Column(name = "telefono", length= 15, unique = true)
    private String telefono;

    @Column(name = "email", length= 100)
    private String email;

    @Column(name = "direccion", length= 750)
    private String direccion;

    @Column(name = "tipo")
    private int tipo;

    @Column(name = "estado")
    private int estado;

    public ProveedorDto toDTO() {
        return ProveedorDto.builder().id(this.id).nombre(this.nombre).encargado(this.encargado).telefono(this.telefono)
                .email(this.email).direccion(this.direccion).tipo(this.tipo).estado(this.estado).build();
    }



}
