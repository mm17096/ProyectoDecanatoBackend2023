package com.ues.edu.apidecanatoce.services.usuario;

import com.ues.edu.apidecanatoce.dtos.empleados.EmpleadoPeticionDto;
import com.ues.edu.apidecanatoce.dtos.usuario.UsuarioPeticionDto;
import com.ues.edu.apidecanatoce.entities.usuario.Usuario;

import java.util.UUID;

public interface IUsuarioService {
    UsuarioPeticionDto leerPorID(String id);
}
