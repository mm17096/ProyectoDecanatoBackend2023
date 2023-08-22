package com.ues.edu.apidecanatoce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_configuracion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigSoliVe {
    @Id
    private int id;

    @Column(name = "use_districts")
    private boolean useDistricts=false;
}
