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
    private String tipo;

    //@NotBlank(message = "El campo foto es bligatorio")
    private String foto;

    private String url;

    @NotBlank(message = "El numero de comprobante es es bligatorio")
    private String comprobante;

    @NotNull(message = "La fecha es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    /*@NotNull(message = "codigo de solicitud es obligatorio")
    private SolicitudvaleDto codigosolicitudvale;*/

    /*public Documentovale toEntitySave() {
        return Documentovale.builder().codigodocumentos(this.id).tipo(this.tipo).foto(this.foto).comprobante(this.comprobante).fecha(this.fecha).build();
    }*/
}
