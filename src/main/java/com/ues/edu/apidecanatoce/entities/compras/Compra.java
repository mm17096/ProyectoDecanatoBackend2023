package com.ues.edu.apidecanatoce.entities.compras;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.dtos.compras.CompraPeticionDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_compra")

public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "codigo_compra")
    private UUID id;

    @Column(name = "factura", length = 100)
    private String factura;

    @ManyToOne
    @JoinColumn(name = "codigo_proveedor", nullable = false,
            foreignKey = @ForeignKey(name = "FK-proveedor"))
    private Proveedor proveedor;

    @Column(name = "descripcion", length = 750)
    private String descripcion;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "codigo_inicio")
    private int cod_inicio;

    @Column(name = "codigo_fin")
    private int cod_fin;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "fecha_hora_compra")
    private LocalDateTime fecha_compra;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_vencimiento_vale")
    private LocalDate fecha_vencimiento;

    @Column(name = "precio_unitario")
    private double precio_unitario;

    public CompraPeticionDto toDTO() {
        return CompraPeticionDto.builder().id(this.id).factura(this.factura)
                .proveedor(this.proveedor.toDTO())
                .descripcion(this.descripcion).cantidad(this.cantidad).cod_inicio(this.cod_inicio)
                .cod_fin(this.cod_fin).fecha_compra(this.fecha_compra).fecha_vencimiento(this.fecha_vencimiento).precio_unitario(this.precio_unitario)
                .total_compra(this.cantidad * this.precio_unitario).build();
    }

    public Compra toDepDTO() {
        return Compra.builder().id(this.id).factura(this.factura)
                .proveedor(this.proveedor)
                .descripcion(this.descripcion).cantidad(this.cantidad).cod_inicio(this.cod_inicio)
                .cod_fin(this.cod_fin).fecha_compra(this.fecha_compra).fecha_vencimiento(this.fecha_vencimiento).precio_unitario(this.precio_unitario).build();
    }

}
