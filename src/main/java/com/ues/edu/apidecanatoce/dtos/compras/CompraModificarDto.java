package com.ues.edu.apidecanatoce.dtos.compras;

import com.ues.edu.apidecanatoce.entities.compras.Compra;
import com.ues.edu.apidecanatoce.entities.compras.Proveedor;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.compras.IProveedorRepository;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.UUID;


@Getter
@Setter
@Builder

public class CompraModificarDto {
    private UUID id;

    @Size(max = 100, message = "La factura debe tener entre 1 y 100 caracteres")
    private String factura;

    @NotNull(message = "Proveerdor es obligatorio")
    private UUID proveedor;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 2, max = 750, message = "La descripción debe tener entre 2 y 750 caracteres")
    private String descripcion;

    public Compra toEntityComplete(IProveedorRepository proveedorRepository) {
        Proveedor proveedorbuscar = proveedorRepository.findById(this.proveedor).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro proveedor"));
        return Compra.builder().id(this.id).factura(this.factura).proveedor(proveedorbuscar).build();
    }

}
