package com.ues.edu.apidecanatoce.dtos.documentovaleDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.entities.documentoVale.Documentovale;
import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.SolicitudValeRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentovaleDto {
    private UUID id;

    @NotBlank(message = "El campo tipo es bligatorio")
    private String tipo;

    //@NotBlank(message = "El campo foto es bligatorio")
    private String foto;

    private  String url;

    @NotBlank(message = "El numero de comprobante es es bligatorio")
    private String comprobante;

    @NotNull(message = "La fecha es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    private UUID solicitudvale;

    /*@NotNull(message = "codigo de solicitud es obligatorio")
    private UUID codigosolicitudvale;*/

    public Documentovale toEntityComplete(SolicitudValeRepository solicitudValerepository) {
        SolicitudVale buscar= solicitudValerepository.findById(this.solicitudvale).orElseThrow(
                ()-> new CustomException(HttpStatus.NOT_FOUND, "No se encontro ninguna solicitud vale"));

        return Documentovale.builder()
                .codigodocumentos(this.id)
                .tipo(this.tipo)
                .foto(this.foto)
                .url(this.url)
                .comprobante(this.comprobante)
                .fecha(this.fecha).solicitudvale(buscar).build();
    }

    /*public Documentovale toEntityComplete() {

        return Documentovale.builder()
                .codigodocumentos(this.id)
                .tipo(this.tipo)
                .foto(this.foto)
                .url(this.url)
                .comprobante(this.comprobante)
                .fecha(this.fecha).build();
    }*/
}
