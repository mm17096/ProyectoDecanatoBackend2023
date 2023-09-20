package com.ues.edu.apidecanatoce.dtos.ajusteVale;

import com.ues.edu.apidecanatoce.entities.ajustesVale.AjusteVale;
import com.ues.edu.apidecanatoce.entities.ajustesVale.DetalleAjusteVale;
import com.ues.edu.apidecanatoce.entities.compras.Compra;
import com.ues.edu.apidecanatoce.entities.compras.Vale;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.ajusteVale.IAjusteValeRepository;
import com.ues.edu.apidecanatoce.repositorys.ajusteVale.IDetalleAjusteValeRepository;
import com.ues.edu.apidecanatoce.repositorys.compras.IValeRepository;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Getter
@Setter
@Builder

public class DetalleAjusteValeDto {

    private UUID id;

    @NotNull(message = "Vale es obligatorio")
    private UUID vale;

    @NotNull(message = "Ajuste de vale es obligatorio")
    private UUID ajusteVale;

    public DetalleAjusteVale toEntityComplete(IAjusteValeRepository ajusteValeRepository, IValeRepository valeRepository) {
        AjusteVale ajustebuscar = ajusteValeRepository.findById(this.ajusteVale).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro ajuste de vale"));
        Vale valebuscar = valeRepository.findById(this.vale).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro vale"));
        return DetalleAjusteVale.builder().id(this.id).vale(valebuscar).ajusteVale(ajustebuscar).build();
    }

}
