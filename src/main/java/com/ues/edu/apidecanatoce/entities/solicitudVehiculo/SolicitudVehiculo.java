package com.ues.edu.apidecanatoce.entities.solicitudVehiculo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.EstadoSolicitudVehiculoDto;
import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoDto;
import com.ues.edu.apidecanatoce.dtos.asignacionValesDto.solicitudes.SolicitudVehiculoModDto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.SolicitudVahiculoConsultaDto;
import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoPeticionDtO;
import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import com.ues.edu.apidecanatoce.entities.usuario.Usuario;
import com.ues.edu.apidecanatoce.entities.vehiculo.Vehiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_solicitud_vehiculo")
public class SolicitudVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "codigo_solicitud_vehiculo")
    private UUID codigoSolicitudVehiculo;

    //Fecha en que se realiza de la solcitud
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDate fechaSolicitud;

    //Fecha de misión
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_salida", nullable = false)
    private LocalDate fechaSalida;

    //Unidad solicitante
    @Column(name = "unidad", length = 50)
    private String unidadSolicitante;

    //Vehiculo solicitado
    @ManyToOne
    @JoinColumn(name = "codigo_vehiculo", nullable = false,
            foreignKey = @ForeignKey(name = "FK_solicitudV_vehiculo"))
    private Vehiculo vehiculo;

    //Objetivo de la misión
    @Column(name = "objetivo", nullable = false)
    private String objetivoMision;

    //insititución a visitar
    @Column(name = "lugar_mision", nullable = false)
    private String lugarMision;

    //Lugar que visitará
    @Column(name = "direccion", nullable = false)
    private String direccion;

    //Hora de regreso de la misión
    @DateTimeFormat(pattern = "HH:mm", iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Column(name = "hora_entrada", nullable = false)
    private LocalTime horaEntrada;

    // Hora de salida de la misión
    @DateTimeFormat(pattern = "HH:mm", iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Column(name = "hora_salida", nullable = false)
    private LocalTime horaSalida;

    //numero de personas asistira la misión
    @Column(name = "cantidad_personas", nullable = false)
    private int cantidadPersonas;

    // listado de personas que asistiran, si son mas de 5 (incluye el responsable)

    @OneToMany(cascade = CascadeType.ALL)
    private List<Pasajeros> listaPasajeros;


    //Responsable de la solicitud
    @ManyToOne
    @JoinColumn(name = "codigo_usuario", nullable = false,
            foreignKey = @ForeignKey(name = "FK_solicitud_vehiculo_usuario"))
    private Usuario usuario;

    //nombre del jefe que aprobo,se obtendra segun el usuario que apruebe
    @Column(name = "jefe_depto", length = 150)
    private String jefeDepto;

    //Feccha de regreso de la mision
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_entrada", nullable = false)
    private LocalDate fechaEntrada;

    // para manejar los estados que va pasando la solicitud
    @Column(name = "estado", nullable = false, columnDefinition = "int default 1")
    private int estado;

    // Mortorista asignado
    @ManyToOne
    @JoinColumn(name = "codigo_motorista", nullable = true,
            foreignKey = @ForeignKey(name = "FK_solicitud_vehiculo_motorista"))
    private Empleado motorista;

    //Lista de documentos que tiene la solicitud de vehiculo

    @OneToMany(mappedBy = "codigoSolicitudVehiculo", cascade = { CascadeType.ALL })
    @JsonManagedReference
    private List<DocumentoSoliCar> listDocumentos;


   // @OneToMany(mappedBy = "solicitudVehiculo", cascade = CascadeType.ALL)
    //private Set<SolicitudVale> solicitudVale = new HashSet<>();

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "tieneVale", columnDefinition = "BOOLEAN DEFAULT true")
    private boolean tieneVale;

    public SolicitudVehiculoPeticionDtO toDto(){
        return SolicitudVehiculoPeticionDtO.builder().codigoSolicitudVehiculo(this.codigoSolicitudVehiculo)
                .fechaSolicitud(this.fechaSolicitud).fechaSalida(this.fechaSalida)
                .unidadSolicitante(this.unidadSolicitante).vehiculo(this.vehiculo.toDTO())
                .objetivoMision(this.objetivoMision).lugarMision(this.lugarMision)
                .direccion(this.direccion).horaEntrada(this.horaEntrada).horaSalida(this.horaSalida)
                .cantidadPersonas(this.cantidadPersonas).listaPasajeros(this.listaPasajeros)
                .solicitante(this.usuario).nombreJefeDepto(this.jefeDepto).fechaEntrada(this.fechaEntrada)
                .estado(this.estado).motorista(this.motorista).listDocumentos(this.listDocumentos)
                .observaciones(this.observaciones).tieneVale(this.tieneVale).build();
    }

    public SolicitudVahiculoConsultaDto toDtot(){
        return SolicitudVahiculoConsultaDto.builder().codigoSolicitudVehiculo(this.codigoSolicitudVehiculo)
                .fechaSolicitud(this.fechaSolicitud).fechaSalida(this.fechaSalida)
                .unidadSolicitante(this.unidadSolicitante).vehiculo(this.vehiculo.toDTO())
                .objetivoMision(this.objetivoMision).lugarMision(this.lugarMision)
                .direccion(this.direccion).horaEntrada(this.horaEntrada).horaSalida(this.horaSalida)
                .cantidadPersonas(this.cantidadPersonas)
                .solicitante(this.usuario).nombreJefeDepto(this.jefeDepto).fechaEntrada(this.fechaEntrada)
                .estado(this.estado).motorista(this.motorista).build();
    }

    public SolicitudVehiculoModDto totoSolicitudVehiculoModDto(){
        return SolicitudVehiculoModDto.builder()
                .idSolicitudVehiculo(this.codigoSolicitudVehiculo)
                .estadoSolicitudVehiculo(this.estado)
                .build();
    }

    public SolicitudVehiculo toDepDTO() {
        return SolicitudVehiculo.builder().codigoSolicitudVehiculo(this.codigoSolicitudVehiculo)
                .fechaSolicitud(this.fechaSolicitud).fechaSalida(this.fechaSalida)
                .unidadSolicitante(this.unidadSolicitante).vehiculo(this.vehiculo)
                .objetivoMision(this.objetivoMision).lugarMision(this.lugarMision)
                .direccion(this.direccion).horaEntrada(this.horaEntrada).horaSalida(this.horaSalida)
                .cantidadPersonas(this.cantidadPersonas).listaPasajeros(this.listaPasajeros)
                .fechaEntrada(this.fechaEntrada)
                .estado(this.estado).motorista(this.motorista).listDocumentos(this.listDocumentos)
                .observaciones(this.observaciones).build();
    }

    public SolicitudVehiculoDto toDto2() {
        return SolicitudVehiculoDto.builder().codigoSolicitudVehiculo(this.codigoSolicitudVehiculo)
                .fechaSolicitud(this.fechaSolicitud).unidadSolicitante(this.unidadSolicitante)
                .objetivoMision(this.objetivoMision).lugarMision(this.lugarMision)
                .horaEntrada(this.horaEntrada).horaSalida(this.horaSalida)
                .cantidadPersonas(this.cantidadPersonas).nombreJefeDepto(this.jefeDepto).fechaEntrada(this.fechaEntrada).estado(this.estado).build();
    }
    public EstadoSolicitudVehiculoDto toEstadoSolicitudVehiculoDto(){
        return EstadoSolicitudVehiculoDto.builder()
                .idSolicitudVehiculo(this.codigoSolicitudVehiculo)
                .estado(this.estado)
                .build();
    }
}
