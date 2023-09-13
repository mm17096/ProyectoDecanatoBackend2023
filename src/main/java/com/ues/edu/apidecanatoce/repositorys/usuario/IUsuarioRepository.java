package com.ues.edu.apidecanatoce.repositorys.usuario;

import com.ues.edu.apidecanatoce.entities.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByNombre(String username);

    Usuario findByCodigoUsuario(String id);
}
