package com.ues.edu.apidecanatoce;

import com.ues.edu.apidecanatoce.controllers.usuario.autenticacion.RegisterRequest;
import com.ues.edu.apidecanatoce.dtos.empleados.EmpleadoDto;
import com.ues.edu.apidecanatoce.dtos.empleados.EmpleadoPeticionDto;
import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import com.ues.edu.apidecanatoce.entities.usuario.Role;
import com.ues.edu.apidecanatoce.repositorys.cargo.ICargoRepository;
import com.ues.edu.apidecanatoce.repositorys.departamentos.IDeptopRepo;
import com.ues.edu.apidecanatoce.repositorys.empleado.IEmpleadoRepository;

import com.ues.edu.apidecanatoce.servicesImpl.cargo.CargoServiceImpl;
import com.ues.edu.apidecanatoce.servicesImpl.departamento.DeptoServiceImp;
import com.ues.edu.apidecanatoce.servicesImpl.estados.EstadosServiceImpl;
import com.ues.edu.apidecanatoce.servicesImpl.usuario.UsuarioServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.SpringVersion;

import java.util.UUID;

import static com.ues.edu.apidecanatoce.entities.usuario.Role.ADMIN;

@SpringBootApplication
public class ApiDecanatoCeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiDecanatoCeApplication.class, args);

        System.out.println("Version de Spring Boot utilizada: " + SpringVersion.getVersion());
    }

    @Bean
    public CommandLineRunner commandLineRunner(IEmpleadoRepository empleadoRepository, UsuarioServiceImpl usuarioService, CargoServiceImpl cargoService, ICargoRepository cargoRepository, DeptoServiceImp deptoServiceImp, IDeptopRepo deptopRepo, EstadosServiceImpl estadosService) {
        return args -> {
            if (!empleadoRepository.existsByCorreo("decanato@ues.edu.sv")) {
                EmpleadoDto data = new EmpleadoDto();

                data.setNombre("Administrador");
                data.setApellido("Defecto");
                data.setCorreo("decanato@ues.edu.sv");
                data.setDui("12345678");
                data.setTelefono("0000-0000");
                data.setNombrefoto("");
                data.setUrlfoto("");
                data.setEstado(estadosService.leerPorNombre("Activo").getCodigoEstado());
                data.setCargo(cargoService.leerPorNombre("Docente").getId());
                data.setDepartamento(deptoServiceImp.leerPorNombre("Ing. De Sistemas").getCodigoDepto());

                //primero se almacena el empleado
                EmpleadoPeticionDto empleadoPeticionDto = empleadoRepository.save(data.toEntityComplete(cargoRepository, deptopRepo)).toDTO();
                Empleado empleado = empleadoRepository.findById(empleadoPeticionDto.getCodigoEmpleado()).orElse(null);
                //se almacena el usuario
                RegisterRequest request = new RegisterRequest();

                request.setNombre(empleadoPeticionDto.getCorreo());
                request.setClave(empleadoPeticionDto.getDui());
                request.setEmpleado(empleadoPeticionDto.getCodigoEmpleado());
                request.setRole(Role.ADMIN);

                System.out.println("Token: " + usuarioService.register(request, empleado).getToken());
            }else{
                System.out.println("Ya existe el usuario admin");
            }
        };
    }
}
