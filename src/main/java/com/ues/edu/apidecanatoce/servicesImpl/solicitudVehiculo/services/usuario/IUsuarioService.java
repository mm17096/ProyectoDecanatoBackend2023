package com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo.services.usuario;

import com.ues.edu.apidecanatoce.dtos.empleados.EmpleadoPeticionDto;
import com.ues.edu.apidecanatoce.dtos.usuario.UsuarioPeticionDto;

import java.util.UUID;

public interface IUsuarioService {
    UsuarioPeticionDto leerPorID(String id);
}
