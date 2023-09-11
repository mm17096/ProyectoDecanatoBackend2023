package com.ues.edu.apidecanatoce;

import com.ues.edu.apidecanatoce.controllers.usuario.autenticacion.RegisterRequest;
import com.ues.edu.apidecanatoce.dtos.empleados.EmpleadoDto;
import com.ues.edu.apidecanatoce.dtos.empleados.EmpleadoPeticionDto;
import com.ues.edu.apidecanatoce.dtos.usuario.UsuarioDto;
import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import com.ues.edu.apidecanatoce.repositorys.ICargoRepository;
import com.ues.edu.apidecanatoce.repositorys.IDeptopRepo;
import com.ues.edu.apidecanatoce.repositorys.empleado.IEmpleadoRepository;
import com.ues.edu.apidecanatoce.repositorys.usuario.IUsuarioRepository;
import com.ues.edu.apidecanatoce.servicesImpl.CargoServiceImpl;
import com.ues.edu.apidecanatoce.servicesImpl.DeptoServiceImp;
import com.ues.edu.apidecanatoce.servicesImpl.empleado.EmpleadoServiceImpl;
import com.ues.edu.apidecanatoce.servicesImpl.estados.EstadosServiceImpl;
import com.ues.edu.apidecanatoce.servicesImpl.usuario.UsuarioServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.SpringVersion;

import java.util.UUID;

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
                System.out.println("Cargo: " + cargoService.leerPorNombre("Docente").getCodigoCargo());
                System.out.println("Depto: " + deptoServiceImp.leerPorNombre("Ing. De Sistemas").getCodigoDepto());
                System.out.println("Estado: " + estadosService.leerPorNombre("Activo").getCodigoEstado());
                data.setEstado(estadosService.leerPorNombre("Activo").getCodigoEstado());
                data.setCargo(cargoService.leerPorNombre("Docente").getCodigoCargo());
                data.setDepartamento(deptoServiceImp.leerPorNombre("Ing. De Sistemas").getCodigoDepto());

                //primero se almacena el empleado
                EmpleadoPeticionDto empleadoPeticionDto = empleadoRepository.save(data.toEntityComplete(cargoRepository, deptopRepo)).toDTO();
                Empleado empleado = empleadoRepository.findById(empleadoPeticionDto.getCodigoEmpleado()).orElse(null);
                //se almacena el usuario
                RegisterRequest request = new RegisterRequest();

                request.setNombre(empleadoPeticionDto.getCorreo());
                request.setClave(empleadoPeticionDto.getDui());
                request.setEmpleado(empleadoPeticionDto.getCodigoEmpleado());

                System.out.println("Token: " + usuarioService.register(request, empleado).getToken());
            }else{
                System.out.println("Ya existe el usuario admin");
            }
        };
    }
}
