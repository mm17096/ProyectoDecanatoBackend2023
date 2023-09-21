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
  ADMIN(Collections.emptySet()),
  USER(Collections.emptySet()),
  JEFE_DEPTO(Collections.emptySet()),

  SECR_DECANATO(Collections.emptySet()),
  DECANO(Collections.emptySet() ),
  ASIS_FINANCIERO(Collections.emptySet()),
  JEFE_FINANACIERO(Collections.emptySet()),
  VIGILANTE(Collections.emptySet()),

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
