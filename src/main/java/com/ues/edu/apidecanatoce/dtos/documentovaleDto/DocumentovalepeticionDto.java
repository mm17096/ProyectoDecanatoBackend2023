package com.ues.edu.apidecanatoce.dtos.documentovaleDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.entities.compras.Compra;
import com.ues.edu.apidecanatoce.entities.documentoVale.Documentovale;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
public class DocumentovalepeticionDto {

    private UUID id;

    @NotBlank(message = "El campo tipo es bligatorio")
    private String tipoDocumento;

    @NotBlank(message = "El campo foto es bligatorio")
    private String fotoDocumentos;

    @NotBlank(message = "El numero de comprobante es es bligatorio")
    private String numeroComprobante;

    @NotNull(message = "La fecha es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @NotNull(message = "codigo de solicitud es obligatorio")
    private SolicitudvaleDto codigosolicitudvale;

    public Documentovale toEntitySave() {
        return Documentovale.builder().codigoDocumentos(this.id).tipoDocumento(this.tipoDocumento).fotoDocumentos(this.fotoDocumentos).numeroComprobante(this.numeroComprobante).fecha(this.fecha).codigosolicitudvale(this.codigosolicitudvale.toEntityComplete()).build();
    }
}
