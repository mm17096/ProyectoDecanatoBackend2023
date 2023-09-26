package com.ues.edu.apidecanatoce.entities.usuario;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_LEER("admin:leer"),
    ADMIN_CREAR("admin:crear"),
    ADMIN_MODIFICAR("admin:modificar"),
    ADMIN_BUSCAR("admin:buscar"),

    DOCENTE_BUSCAR("docente:buscar"),
  
    DOCENTE_LEER("docente:leer"),

    USER_LEER  ("user:leer"),
    USER_CREAR("user:crear"),

    USER_MODIFICAR("user:modificar"),

    USER_BUSCAR("user:buscar"),

    JEFE_DEPTO_LEER  ("jefe_depto:leer"),
    JEFE_DEPTO_CREAR("jefe_depto:crear"),

    JEFE_DEPTO_MODIFICAR("jefe_depto:modificar"),

    JEFE_DEPTO_BUSCAR("jefe_depto:buscar"),

    SECR_DECANATO_LEER  ("secr_decanato:leer"),
    SECR_DECANATO_CREAR("secr_decanato:crear"),

    SECR_DECANATO_MODIFICAR("secr_decanato:modificar"),

    SECR_DECANATO_BUSCAR("secr_decanato:buscar"),

    DECANO_LEER  ("decano:leer"),
    DECANO_CREAR("decano:crear"),

    DECANO_MODIFICAR("decano:modificar"),

    DECANO_BUSCAR("decano:buscar"),

    JEFE_FINANACIERO_LEER  ("jefe_financiero:leer"),
    JEFE_FINANACIERO_CREAR("jefe_financiero:crear"),

    JEFE_FINANACIERO_MODIFICAR("jefe_financiero:modificar"),

    JEFE_FINANACIERO_BUSCAR("jefe_financiero:buscar"),

    ASIS_FINANCIERO_LEER  ("asis_financiero:leer"),
    ASIS_FINANCIERO_CREAR("asis_financiero:crear"),

    ASIS_FINANCIERO_MODIFICAR("asis_financiero:modificar"),

    ASIS_FINANCIERO_BUSCAR("asis_financiero:buscar"),

    VIGILANTE_LEER  ("vigilante:leer"),
    VIGILANTE_CREAR("vigilante:crear"),

    VIGILANTE_MODIFICAR("vigilante:modificar"),

    VIGILANTE_BUSCAR("vigilante:buscar"),

    ;

    @Getter
    private final String permission;

}
