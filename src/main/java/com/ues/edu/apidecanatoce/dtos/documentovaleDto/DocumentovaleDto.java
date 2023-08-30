package com.ues.edu.apidecanatoce.dtos.documentovaleDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.entities.SolicitudVale;
import com.ues.edu.apidecanatoce.entities.documentoVale.Documentovale;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.SolicitudValeRepository;
import com.ues.edu.apidecanatoce.repositorys.documentoVale.Documentosrepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
public class DocumentovaleDto {
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
    private UUID codigosolicitudvale;

    public Documentovale toEntityComplete(SolicitudValeRepository solicitudvalerepository) {
        SolicitudVale solicitudbuscar=solicitudvalerepository.findById(this.codigosolicitudvale).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentró solicitud"));
        return Documentovale.builder().codigoDocumentos(this.id).tipoDocumento(this.tipoDocumento).fotoDocumentos(this.fotoDocumentos).numeroComprobante(this.numeroComprobante).fecha(this.fecha).codigosolicitudvale(solicitudbuscar).build();
    }
}
