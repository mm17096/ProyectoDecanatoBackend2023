package com.ues.edu.apidecanatoce.dtos.sendgrid;


import com.ues.edu.apidecanatoce.entities.sendgrid.Sendgrid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendGridDto {
    private UUID codigoSendgrid;

    @NotBlank(message = "El keysendgrid es obligatorio")
    private String keysendgrid;

    @NotBlank(message = "El keyplantilla es obligatorio")
    private String keyplantilla;

    public Sendgrid toEntityComplete() {
        return Sendgrid.builder().codigoSendgrid(this.codigoSendgrid).keysendgrid(this.keysendgrid).keyplantilla(this.keyplantilla).build();
    }
}
