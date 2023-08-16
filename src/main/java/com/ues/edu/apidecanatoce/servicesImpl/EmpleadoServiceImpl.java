package com.ues.edu.apidecanatoce.servicesImpl;

import com.ues.edu.apidecanatoce.entities.Empleado;
import com.ues.edu.apidecanatoce.repositorys.IEmpleadoRepository;
import com.ues.edu.apidecanatoce.services.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    private final IEmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoServiceImpl(IEmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public Empleado registrar(Empleado obj) {
        return this.empleadoRepository.save(obj);
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
        return  this.empleadoRepository.buscarEmpleado(filtro) ;
    }


    @Override
    public Empleado leerPorId(Integer id) {
        return this.empleadoRepository.findById(id).get();
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


}
