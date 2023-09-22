package com.ues.edu.apidecanatoce.entities.usuario;

import com.ues.edu.apidecanatoce.dtos.usuario.UsuarioPeticionDto;
import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_usuario", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre"})})
public class Usuario implements UserDetails {
    @Id
    @Column(name = "codigo_usuario")
    private String codigoUsuario;

    @Basic
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "calve")
    private String clave;

    @Column(name = "nuevo")
    private boolean nuevo;
/*
no se esta usando
    @Column(name = "activo")
    private boolean activo;
*/
    @Column(name = "token")
    private String token;

    @Enumerated(EnumType.STRING)
    private Role role;



    @OneToOne
    @JoinColumn(name = "id_empleado", nullable = false,
            foreignKey = @ForeignKey(name = "FK_usuario_empleado"))
    private Empleado empleado;

    public UsuarioPeticionDto toDTO() {
        return UsuarioPeticionDto.builder().codigoUsuario(this.codigoUsuario).nombre(this.nombre).clave(this.clave)
                .nuevo(this.nuevo).role(this.role).empleado(this.empleado).build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         //  return List.of(new SimpleGrantedAuthority((role.name())));
       return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return this.clave;
    }

    @Override
    public String getUsername() {
        return this.nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
