package com.ues.edu.apidecanatoce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_pasajeros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pasajeros {
    @Id
    private String id;

    @Column(name = "nombre", length = 150)
    private String nombrePasajero;

}
