package com.ues.edu.apidecanatoce.repositorys.usuario;

import com.ues.edu.apidecanatoce.controllers.usuario.controlador.DatosCards;
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
    Usuario findByEmpleadoCorreo(String correo);
    boolean existsByEmpleadoCorreo(String correo);
    boolean existsByEmpleadoDui(String dui);
    @Query("SELECT u.codigoUsuario " +
            "FROM Usuario u WHERE u.nombre = :username")
    String findIdByUsername(@Param("username") String username);

    //para optener los datos de las card del Panel de control
    @Query(value = "SELECT (SELECT COUNT(tv.id_vale) FROM tb_vale tv WHERE tv.estado = 8) as vales, (SELECT COUNT(tsv.codigo_solicitud_vehiculo) FROM tb_solicitud_vehiculo tsv WHERE tsv.estado != 7) as misiones, (SELECT COUNT(tsv.codigo_solicitud_vehiculo) FROM tb_solicitud_vehiculo tsv WHERE tsv.estado = 5 AND tsv.fecha_salida = CURRENT_DATE) as misioneshoy, (SELECT COUNT(tsv.codigo_solicitud_vehiculo) FROM tb_solicitud_vehiculo tsv WHERE tsv.estado = 7 AND EXTRACT(MONTH FROM tsv.fecha_salida) = EXTRACT(MONTH FROM CURRENT_DATE)) as misionesmes", nativeQuery = true)
    DatosCards datoscard();
}
