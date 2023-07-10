package com.ues.edu.apidecanatoce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_documentos")
public class Documentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigoDocumentos;

    @Column(name = "tipo", length = 25)
    private String tipoDocumento;

    @Column(name = "foto")
    private String fotoDocumentos;

    @Column(name = "n_comprobante")
    private String numeroComprobante;

    @Column(name = "fecha")
    private LocalDate fecha;
}
