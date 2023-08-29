package com.ues.edu.apidecanatoce.entities.AsignacionVales;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.AsignacionValeDto;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.AsignacionValeOutDto;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.DetalleAsignacionDto;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.ValeDto;
import com.ues.edu.apidecanatoce.entities.SolicitudVale;
import com.ues.edu.apidecanatoce.repositorys.asignacionvale.IAsignacionValeRepository;
import com.ues.edu.apidecanatoce.repositorys.asignacionvale.IDetalleAsignacionRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="tb_asignacion_vale")
public class AsignacionVale {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "codigo_asignacion")
    private UUID codigoAsignacion;

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

    public AsignacionValeDto toAsignacionValeDTO(){
        return AsignacionValeDto.builder()
                .codigoAsignacion(this.codigoAsignacion)
                .estadoAsignacion(this.estado)
                .fechaAsignacion(this.fecha)
                .solicitudVale(this.solicitudVale)
                .build();
    }


    public AsignacionValeOutDto toDto(IAsignacionValeRepository asignacionValeRepository, IDetalleAsignacionRepository detalleAsignacionRepository, UUID id){
        //sSe busca la asiganción por ID
        AsignacionVale asignacionVale = asignacionValeRepository.findById(id).orElseThrow();

       // Listando los detalles
        List<DetalleAsignacionVale> detalleAsignacionValeList = detalleAsignacionRepository.findAll();

        // Se usa para guardar los detalles de la asignación
        List<DetalleAsignacionDto> detalleAsignacionDtoList = new ArrayList<>();
        for (DetalleAsignacionVale detalleAsignacionValeE : detalleAsignacionValeList
             ) {
            detalleAsignacionDtoList.add(detalleAsignacionValeE.toDTODetalle());
        }

        List<ValeDto> vales = new ArrayList<>();
        int i = 0;
        while (i < detalleAsignacionRepository.findAll().size()){
            // Comparo si el ID de la asignación es igual al ID de la asignación del detalle
            if (detalleAsignacionDtoList.get(i).getCodigoAsignacionVale().equals(asignacionVale.codigoAsignacion)){
                // Si es así, se guarda en el arreglo de vales la clase completa del vale
                vales.add(detalleAsignacionDtoList.get(i).getVale().toValeDto());
            }
            i++;
        }

        //Retorno la DTO que necesito
        return AsignacionValeOutDto.builder()
                .idAsignacionVale(this.codigoAsignacion)
                .fechaAsignacion(this.fecha)
                .mision(this.solicitudVale.getSolicitudVehiculo().getObjetivoMision() + ", " +
                        this.solicitudVale.getSolicitudVehiculo().getLugarMision())
                .vales(vales)
                .build();
    }

}
