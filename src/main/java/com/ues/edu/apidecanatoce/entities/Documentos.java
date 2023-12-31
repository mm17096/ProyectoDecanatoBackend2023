package com.ues.edu.apidecanatoce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_documentos")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "codigoDocumentos")
public class Documentos {
    @Id
    private String codigoDocumentos;

    @Column(name = "tipo")
    private String tipoDocumento;

    @Column(name = "foto")
    private String fotoDocumentos;

    @Column(name = "n_comprobante")
    private String numeroComprobante;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha")
    private LocalDate fecha;


}
