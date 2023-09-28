package com.ues.edu.apidecanatoce.servicesImpl.empleado;


import com.ues.edu.apidecanatoce.controllers.usuario.autenticacion.RegisterRequest;
import com.ues.edu.apidecanatoce.dtos.empleados.EmpleadoDto;
import com.ues.edu.apidecanatoce.dtos.empleados.EmpleadoPeticionDto;
import com.ues.edu.apidecanatoce.entities.cargos.Cargo;

import com.ues.edu.apidecanatoce.entities.departamentos.Departamento;
import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import com.ues.edu.apidecanatoce.exceptions.CustomException;

import com.ues.edu.apidecanatoce.repositorys.cargo.ICargoRepository;
import com.ues.edu.apidecanatoce.repositorys.departamentos.IDeptopRepo;


import com.ues.edu.apidecanatoce.repositorys.empleado.IEmpleadoRepository;
import com.ues.edu.apidecanatoce.services.empleado.IEmpleadoService;

import com.ues.edu.apidecanatoce.servicesImpl.cargo.CargoServiceImpl;
import com.ues.edu.apidecanatoce.servicesImpl.usuario.UsuarioServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    private final AuthenticationManager authenticationManager;

    private final IEmpleadoRepository empleadoRepository;

    private final UsuarioServiceImpl usuarioService;

    private final ICargoRepository cargoRepository;

    private final CargoServiceImpl cargoService;

    private final IDeptopRepo deptopRepo;


    @Autowired

    public EmpleadoServiceImpl(AuthenticationManager authenticationManager, IEmpleadoRepository empleadoRepository, UsuarioServiceImpl usuarioService, ICargoRepository cargoRepository, CargoServiceImpl cargoService, IDeptopRepo deptopRepo) {
        this.authenticationManager = authenticationManager;
        this.empleadoRepository = empleadoRepository;
        this.usuarioService = usuarioService;
        this.cargoRepository = cargoRepository;
        this.cargoService = cargoService;
        this.deptopRepo = deptopRepo;
    }

    ///////// Metodos reestructurados /////////
    @Override
    public EmpleadoPeticionDto registrar(EmpleadoDto data) {
        Departamento departamento = deptopRepo.findById(data.getDepartamento()).orElse(null);
        Cargo cargo = cargoRepository.findById(data.getCargo()).orElse(null);

        if (empleadoRepository.existsByDui(data.getDui())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El DUI ya está registrado");
        }
        //metodo para verificar si ya existe un correo igual almacenada
        if (empleadoRepository.existsByCorreo(data.getCorreo())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El Correo ya está registrado");
        }
        //metodo para verificar si ya existe una licencia igual almacenada
        if (!data.getLicencia().isEmpty()) {
            if (empleadoRepository.existsByLicencia(data.getLicencia())) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "La Licencia ya está registrada");
            }
        }

        //metodo para verificar si ya existe un decano
        if (cargo.getNombreCargo().equals("DECANO")) {
            if (empleadoRepository.existsByCargo(cargo)) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "Ya existe un Decano");
            }
        }

        //metodo para verificar si ya existe un jefe de departamento
        if (cargo.getNombreCargo().equals("JEFE DEPARTAMENTO")) {
            if (departamento != null && cargo != null) {
                if (empleadoRepository.existsByDepartamentoAndCargo(departamento, cargo)) {
                    throw new CustomException(HttpStatus.BAD_REQUEST, "Ya existe un jefe en el departamento");
                }
            }
        }

        //primero se almacena el empleado
        EmpleadoPeticionDto empleadoPeticionDto = empleadoRepository.save(data.toEntityComplete(cargoRepository, deptopRepo)).toDTO();

        //se verifica si el cargo seleccionado es diferente a motorista, para agregarle usuario
        if (data.getCargo() != cargoService.leerPorNombre("Motorista").getId()) {
            RegisterRequest request = new RegisterRequest();

            request.setNombre(empleadoPeticionDto.getCorreo());
            request.setClave(empleadoPeticionDto.getDui());
            request.setEmpleado(empleadoPeticionDto.getCodigoEmpleado());

            Empleado empleado = empleadoRepository.findById(request.getEmpleado()).orElse(null); //Buscamos el empleado y lo mandamos a insertar

            System.out.println("Token: " + usuarioService.register(request, empleado).getToken()); //Almacenamos el usuario y devolvemos token
        }

        //retornamos segun la funcion lo requiera
        return empleadoPeticionDto;
    }


    @Override
    public Page<EmpleadoPeticionDto> listar(Pageable pageable) {
        Page<Empleado> compras = empleadoRepository.findAll(pageable);
        return compras.map(Empleado::toDTO);
    }

    @Override
    public EmpleadoPeticionDto leerPorId(UUID id) {
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro empleado"));
        return empleado.toDTO();
    }

    @Override
    public EmpleadoPeticionDto actualizar(UUID id, EmpleadoDto data) {
        EmpleadoPeticionDto buscarEmpleado = leerPorId(id);

        Departamento departamento = deptopRepo.findById(data.getDepartamento()).orElse(null);
        Cargo cargo = cargoRepository.findById(data.getCargo()).orElse(null);

        if (empleadoRepository.existsByDuiAndCodigoEmpleadoNot(data.getDui(), id)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El DUI ya está registrado");
        }

        if (empleadoRepository.existsByCorreoAndCodigoEmpleadoNot(data.getCorreo(), id)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El Correo ya está registrado");
        }

        if (!data.getLicencia().isEmpty()) {
            if (empleadoRepository.existsByLicenciaAndCodigoEmpleadoNot(data.getLicencia(), id)) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "La Licencia ya está registrada");
            }
        }

        if (cargo.getNombreCargo().equals("DECANO")) {
            if (empleadoRepository.existsByCargoAndCodigoEmpleadoNot(cargo, id)) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "Ya existe un Decano");
            }
        }

        if (cargo.getNombreCargo().equals("JEFE DEPARTAMENTO")) {
            if (departamento != null && cargo != null) {
                if (empleadoRepository.existsByDepartamentoAndCargoAndCodigoEmpleadoNot(departamento, cargo, id)) {
                    throw new CustomException(HttpStatus.BAD_REQUEST, "Ya existe un jefe en el departamento");
                }
            }
        }

        data.setCodigoEmpleado(id);
        EmpleadoPeticionDto empleadoPeticionDto = empleadoRepository.save(data.toEntityComplete(cargoRepository, deptopRepo)).toDTO();
        //codigo agregado para probar el modificar el rol si el cargo cambio
            if(!buscarEmpleado.getCargo().getNombreCargo().equals(cargo.getNombreCargo()) ){

                if (data.getCargo() != cargoService.leerPorNombre("MOTORISTA").getId()) {
                    RegisterRequest request = new RegisterRequest();

                    request.setNombre(empleadoPeticionDto.getCorreo());
                    request.setClave(empleadoPeticionDto.getDui());
                    request.setEmpleado(empleadoPeticionDto.getCodigoEmpleado());

                    Empleado empleado = empleadoRepository.findById(request.getEmpleado()).orElse(null); //Buscamos el empleado y lo mandamos a insertar

                    usuarioService.modificar(request, empleado); //Almacenamos el usuario
                }
            }
        // final del segmento -----------------------------------------

       // return empleadoRepository.save(data.toEntityCompletes(cargoRepository, deptopRepo)).toDTO();
            return empleadoPeticionDto;
    }
}
