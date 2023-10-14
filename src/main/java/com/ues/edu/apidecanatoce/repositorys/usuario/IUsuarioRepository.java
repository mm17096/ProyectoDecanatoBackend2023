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
    @Query(value = "SELECT (SELECT COUNT(tv.id_vale) FROM tb_vale tv WHERE tv.estado = 8) as vales, (SELECT COUNT(tsv.codigo_solicitud_vehiculo) FROM tb_solicitud_vehiculo tsv WHERE tsv.estado < 7) as misiones, (SELECT COUNT(tsv.codigo_solicitud_vehiculo) FROM tb_solicitud_vehiculo tsv WHERE tsv.estado = 5 AND tsv.fecha_salida = CURRENT_DATE) as misioneshoy, (SELECT COUNT(tsv.codigo_solicitud_vehiculo) FROM tb_solicitud_vehiculo tsv WHERE tsv.estado = 7 AND EXTRACT(MONTH FROM tsv.fecha_salida) = EXTRACT(MONTH FROM CURRENT_DATE)) as misionesmes, (SELECT COUNT(*) FROM tb_empleado as emp INNER JOIN tb_cargo cargo ON emp.id_cargo = cargo.\"id\" WHERE emp.estado = 8 AND cargo.nombre_cargo = 'MOTORISTA' AND emp.codigo_empleado NOT IN ( SELECT tsv.codigo_motorista FROM tb_solicitud_vehiculo as tsv WHERE (tsv.fecha_salida BETWEEN CURRENT_DATE AND CURRENT_DATE OR tsv.fecha_entrada  BETWEEN CURRENT_DATE AND CURRENT_DATE) AND tsv.estado  > 2 AND tsv.estado  < 7 ) OR emp.codigo_empleado IN ( SELECT tsv.codigo_motorista FROM tb_solicitud_vehiculo as tsv WHERE (tsv.fecha_salida BETWEEN CURRENT_DATE AND CURRENT_DATE OR tsv.fecha_entrada  BETWEEN CURRENT_DATE AND CURRENT_DATE) AND tsv.codigo_solicitud_vehiculo in ( SELECT ensal.codigo_solicitud_vehiculo FROM tb_entrada_salida as ensal WHERE ensal.estado = 2) )) as motoristas", nativeQuery = true)
    DatosCards datoscard();
}
