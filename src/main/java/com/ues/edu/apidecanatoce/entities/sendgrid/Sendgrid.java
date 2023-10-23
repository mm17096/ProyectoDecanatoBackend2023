package com.ues.edu.apidecanatoce.entities.sendgrid;

import com.ues.edu.apidecanatoce.dtos.cargosDto.CargosDto;
import com.ues.edu.apidecanatoce.dtos.sendgrid.SendGridDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Data
@Table(name="tb_sendgrid")
public class Sendgrid {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name ="codigo_sendgrid")
    private UUID codigoSendgrid;

    @Column(name = "keysendgrid", unique = true)
    private String keysendgrid;

    @Column(name = "keyplantilla", unique = true)
    private String keyplantilla;


    public SendGridDto toDto(){
        return  SendGridDto.builder().codigoSendgrid(this.codigoSendgrid).keysendgrid(this.keysendgrid)
                .keyplantilla(this.keyplantilla).build();
    }
}
