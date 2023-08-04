package com.ues.edu.apidecanatoce.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="codigo_usuario")
    private int codigoUsuario;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
    // abajo hay relaciones





























    @OneToMany(mappedBy="codigoUsuario", cascade= { CascadeType.ALL })
    @JsonManagedReference
    private List<SolicitudVehiculo> listSolicitudes;



}
