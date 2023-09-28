package com.ues.edu.apidecanatoce.entities.solicitudVehiculo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_documentosolicar")
public class DocumentoSoliCar {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "codigo_documento")
    private UUID codigoDocumento;

    @Column(name = "nombredocment")
    private String nombreDocumento;

    @Column(name = "urldocument")
    private String urlDocumento;

    @Column(name = "tipodocument")
    private String tipoDocumento;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "codigoSolicitudVehiculo", nullable = true,
            foreignKey = @ForeignKey(name = "FK_documentos_soli_vehiculo"))
    @JsonBackReference
    private SolicitudVehiculo codigoSolicitudVehiculo;
}
