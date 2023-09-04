package com.ues.edu.apidecanatoce.entities.documentoVale;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.dtos.documentovaleDto.DocumentovaleDto;
import com.ues.edu.apidecanatoce.dtos.documentovaleDto.DocumentovalepeticionDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_documentosvale")
public class Documentovale {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codigodocumentos;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "foto")
    private String foto;

    @Column(name = "url")
    private String url;

    @Column(name = "comprobante")
    private String comprobante;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha")
    private LocalDate fecha;

    /*public DocumentovalepeticionDto toDTO() {
        return DocumentovalepeticionDto.builder()
                .id(this.codigodocumentos)
                .tipo(this.tipo)
                .foto(this.foto)
                .url(this.url)
                .comprobante(this.comprobante)
                .fecha(this.fecha).build();
    }*/public DocumentovaleDto toDTO() {
        return DocumentovaleDto.builder()
                .id(this.codigodocumentos)
                .tipo(this.tipo)
                .foto(this.foto)
                .url(this.url)
                .comprobante(this.comprobante)
                .fecha(this.fecha).build();
    }


    /*public Documentovale toDepDTO() {
        return Documentovale.builder().codigodocumentos(this.codigodocumentos).tipo(this.tipo).foto(this.foto).comprobante(this.comprobante).fecha(this.fecha).build();
    }*/


    //para mostrar en tabla
    public DocumentovaleDto toDTO2() {
        return DocumentovaleDto.builder().id(this.codigodocumentos).tipo(this.tipo).foto(this.foto).comprobante(this.comprobante).fecha(this.fecha).build();
    }

}
