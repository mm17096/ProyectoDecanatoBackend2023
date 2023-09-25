package com.ues.edu.apidecanatoce.entities.usuario;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ues.edu.apidecanatoce.entities.usuario.Permission.*;

@RequiredArgsConstructor
public enum Role {
  ADMIN(Set.of(
          ADMIN_LEER,
          ADMIN_CREAR,
          ADMIN_MODIFICAR,
          ADMIN_BUSCAR
  )),
  USER(Set.of(
          USER_LEER,
          USER_CREAR,
          USER_MODIFICAR,
          USER_BUSCAR
  )),
  JEFE_DEPTO(Set.of(
          JEFE_DEPTO_LEER,
          JEFE_DEPTO_CREAR,
          JEFE_DEPTO_MODIFICAR,
          JEFE_DEPTO_BUSCAR
  )),

  SECR_DECANATO(Set.of(
          SECR_DECANATO_LEER,
          SECR_DECANATO_CREAR,
          SECR_DECANATO_MODIFICAR,
          SECR_DECANATO_BUSCAR
  )),
  DECANO(Set.of(
          DECANO_LEER,
          DECANO_CREAR,
          DECANO_MODIFICAR,
          DECANO_BUSCAR
  ) ),
  ASIS_FINANCIERO(Set.of(
          ASIS_FINANCIERO_LEER,
          ASIS_FINANCIERO_CREAR,
          ASIS_FINANCIERO_MODIFICAR,
          ASIS_FINANCIERO_BUSCAR
  )),
  JEFE_FINANACIERO(Set.of(
          JEFE_FINANACIERO_LEER,
          JEFE_FINANACIERO_CREAR,
          JEFE_FINANACIERO_MODIFICAR,
          JEFE_FINANACIERO_BUSCAR
  )),
  VIGILANTE(Set.of(
          VIGILANTE_LEER,
          VIGILANTE_CREAR,
          VIGILANTE_MODIFICAR,
          VIGILANTE_BUSCAR
  )),

  ;

  @Getter
  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities(){
    var authorities =  getPermissions()
            .stream()
            .map( permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }

}
