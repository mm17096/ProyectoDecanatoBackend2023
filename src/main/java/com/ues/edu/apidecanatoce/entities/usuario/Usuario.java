package com.ues.edu.apidecanatoce.entities.usuario;

import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="tb_usuario")
public class Usuario {
    @Id
    @Column(name ="codigo_usuario")
    private UUID codigoUsuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "calve")
    private String clave;

    @Column(name = "nuevo")
    private boolean nuevo;

    @OneToOne
    @JoinColumn(name = "id_empleado", nullable = false,
            foreignKey = @ForeignKey(name = "FK_usuario_empleado"))
    private Empleado empleado;


}
