package com.ues.edu.apidecanatoce.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_logvale")
public class LogVale {
    @Id
    @Column(name = "id_logvale")
    private String idLogVale;

    @Column(name = "estado_vale")
    private String estadoVale;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_logvale", nullable = false)
    private LocalDate fechaLogVale;

    @Column(name = "actividad")
    private String actividad;

    @Column(name = "usuario")
    private String usuario;

    @ManyToOne
    @JoinColumn(name = "id_vale")
    private Vale vale;


}
