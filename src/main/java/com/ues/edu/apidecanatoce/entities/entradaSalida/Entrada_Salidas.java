package com.ues.edu.apidecanatoce.entities.entradaSalida;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.dtos.entradasalidaDto.EntradasalidaDto;
import com.ues.edu.apidecanatoce.dtos.entradasalidaDto.EntradasalidaPeticionDto;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="tb_EntradaSalida")
public class Entrada_Salidas {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "codigo_entradasalida")
    private UUID codigoEntradaSalida;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "hora")
    private LocalTime hora;

    @Column(name = "combustible")
    private String combustible;

    @Column(name = "kilometraje")
    private String kilometraje;

    @Column(name = "estado")
    private int estado;

    @ManyToOne
    @JoinColumn(name = "codigoSolicitudVehiculo", nullable = false,
            foreignKey = @ForeignKey(name = "FK_solicitudvehiculo_entradasalida"))
    private SolicitudVehiculo solicitudvehiculo;

    public EntradasalidaPeticionDto toDTO() {
        return EntradasalidaPeticionDto.builder().id(this.codigoEntradaSalida)
                .fecha(this.fecha).hora(this.hora)
                .combustible(this.combustible)
                .kilometraje(this.kilometraje).estado(this.estado)
                .solicitudvehiculo(this.solicitudvehiculo.toDto2()).build();
    }

}
