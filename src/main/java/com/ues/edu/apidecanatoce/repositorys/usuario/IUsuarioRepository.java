package com.ues.edu.apidecanatoce.repositorys.usuario;

import com.ues.edu.apidecanatoce.entities.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface IUsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByNombre(String username);
    Usuario findByCodigoUsuario(String id);
    Usuario findUsuarioByNombre(String nombre);

    @Query("SELECT u.codigoUsuario " +
            "FROM Usuario u WHERE u.nombre = :username")
    String findIdByUsername(@Param("username") String username);

}
