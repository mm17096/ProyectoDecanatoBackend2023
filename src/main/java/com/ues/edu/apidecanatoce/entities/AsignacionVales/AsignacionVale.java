package com.ues.edu.apidecanatoce.entities.AsignacionVales;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.entities.SolicitudVale;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_asignacion_vale")
public class AsignacionVale {
    @Id
    @Column(name = "codigo_asignacion")
    private String codigoAsignacion;

    @Column(name = "estado")
    private Integer estado;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "solicitud_vale_id", referencedColumnName = "id_solicitud_vale")
    private SolicitudVale solicitudVale;

    @OneToMany(mappedBy = "asignacionVale")
    private Set<DetalleAsignacionVale> detalleAsignacionValeSet;
}
