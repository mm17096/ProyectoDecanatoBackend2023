package com.ues.edu.apidecanatoce.servicesImpl;

import com.ues.edu.apidecanatoce.dtos.empleados.EmpleadoDto;
import com.ues.edu.apidecanatoce.dtos.empleados.EmpleadoPeticionDto;
import com.ues.edu.apidecanatoce.entities.Empleado;
import com.ues.edu.apidecanatoce.repositorys.ICargoRepository;
import com.ues.edu.apidecanatoce.repositorys.IDeptopRepo;
import com.ues.edu.apidecanatoce.repositorys.IEmpleadoRepository;
import com.ues.edu.apidecanatoce.services.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public Empleado registrar(Empleado obj) {
        return this.empleadoRepository.save(obj);
    }

    @Override
    public EmpleadoPeticionDto registrar(EmpleadoDto data) {
        return empleadoRepository.save(data.toEntityComplete(cargoRepository, deptopRepo)).toDTO();
    }

    @Override
    public Empleado modificar(Empleado obj) {
        return null;
    }

    @Override
    public List<Empleado> listar() {
        List<Empleado> listConsultas = this.empleadoRepository.findAll();
        return listConsultas;
    }


    @Override
    public List<Empleado> buscarEmpleado(String filtro) {
        return this.empleadoRepository.buscarEmpleado(filtro);
    }

    @Override
    public Empleado leerPorId(UUID dui) {
        return this.empleadoRepository.findById(dui).get();
    }

    @Override
    public boolean eliminar(Empleado obj) {
        try {
            this.empleadoRepository.delete(obj);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

    @Override
    public List<Empleado> listarPorEstado(int estado) {
        return null;
    }
}
