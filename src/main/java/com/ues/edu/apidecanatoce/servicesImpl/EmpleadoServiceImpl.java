package com.ues.edu.apidecanatoce.servicesImpl;


import com.ues.edu.apidecanatoce.dtos.empleados.EmpleadoDto;
import com.ues.edu.apidecanatoce.dtos.empleados.EmpleadoPeticionDto;
import com.ues.edu.apidecanatoce.entities.Empleado;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.cargo.ICargoRepository;
import com.ues.edu.apidecanatoce.repositorys.departamentos.IDeptopRepo;
import com.ues.edu.apidecanatoce.repositorys.IEmpleadoRepository;
import com.ues.edu.apidecanatoce.services.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    private final IEmpleadoRepository empleadoRepository;

    private final ICargoRepository cargoRepository;

    private final IDeptopRepo deptopRepo;

    @Autowired
    public EmpleadoServiceImpl(IEmpleadoRepository empleadoRepository, ICargoRepository cargoRepository, IDeptopRepo deptopRepo) {
        this.empleadoRepository = empleadoRepository;
        this.cargoRepository = cargoRepository;
        this.deptopRepo = deptopRepo;
    }

    ///////// Metodos reestructurados /////////
    @Override
    public EmpleadoPeticionDto registrar(EmpleadoDto data) {
        if (empleadoRepository.existsByDui(data.getDui())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El DUI ya está registrado");
        }
        if (empleadoRepository.existsByCorreo(data.getCorreo())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El Correo ya está registrado");
        }
        if(!data.getLicencia().isEmpty()){
            if (empleadoRepository.existsByLicencia(data.getLicencia())) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "La Licencia ya está registrada");
            }
        }
        return empleadoRepository.save(data.toEntityComplete(cargoRepository, deptopRepo)).toDTO();
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
        if (empleadoRepository.existsByDuiAndCodigoEmpleadoNot(data.getDui(), id)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El DUI ya está registrado");
        }
        if (empleadoRepository.existsByCorreoAndCodigoEmpleadoNot(data.getCorreo(), id)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El Correo ya está registrado");
        }
        if(!data.getLicencia().isEmpty()){
            if (empleadoRepository.existsByLicenciaAndCodigoEmpleadoNot(data.getLicencia(), id)) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "La Licencia ya está registrada");
            }
        }
        data.setCodigoEmpleado(id);
        return empleadoRepository.save(data.toEntityComplete(cargoRepository, deptopRepo)).toDTO();
    }
}
