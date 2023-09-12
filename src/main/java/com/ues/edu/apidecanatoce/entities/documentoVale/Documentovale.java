package com.ues.edu.apidecanatoce.entities.documentoVale;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.dtos.documentovaleDto.DocumentovaleDto;
import com.ues.edu.apidecanatoce.dtos.documentovaleDto.DocumentovalepeticionDto;
import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_documentosvale")
public class Documentovale {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codigoDocumentos;

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


    @ManyToOne
    @JoinColumn(name = "id_solicitud_vale", nullable = false, foreignKey = @ForeignKey(name = "FK_documentos_soli_Vale"))
    private SolicitudVale codigosolicitudvale;


    public DocumentovalepeticionDto toDTO() {
        return DocumentovalepeticionDto.builder().id(this.codigoDocumentos).tipoDocumento(this.tipoDocumento).numeroComprobante(this.numeroComprobante).fecha(this.fecha).codigosolicitudvale(this.codigosolicitudvale.toDTO()).build();
    }
    public Documentovale toDepDTO() {
        return Documentovale.builder().codigoDocumentos(this.codigoDocumentos).tipoDocumento(this.tipoDocumento).fotoDocumentos(this.fotoDocumentos).numeroComprobante(this.numeroComprobante).fecha(this.fecha).codigosolicitudvale(this.codigosolicitudvale).build();
    }

    public DocumentovaleDto toDTO2() {
        return DocumentovaleDto.builder().id(this.codigoDocumentos).tipoDocumento(this.tipoDocumento).fotoDocumentos(this.fotoDocumentos).numeroComprobante(this.numeroComprobante).fecha(this.fecha).codigosolicitudvale(this.codigosolicitudvale.getIdSolicitudVale()).build();
    }

}
