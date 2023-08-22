package com.ues.edu.apidecanatoce.servicesImpl;

import com.ues.edu.apidecanatoce.entities.Departamento;
import com.ues.edu.apidecanatoce.repositorys.IDeptopRepo;
import com.ues.edu.apidecanatoce.services.IDeptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptoServiceImp implements IDeptoService {

    private final IDeptopRepo deptopService;

    @Autowired
    public DeptoServiceImp(IDeptopRepo deptopService){
        this.deptopService = deptopService;
    }

    @Override
    public List<Departamento> listar(){
       List<Departamento> listDepto = this.deptopService.findAll();
       return listDepto;
    }

    @Override
    public Departamento registrar(Departamento obj){return this.deptopService.save(obj);}


    @Override
    public Departamento modificar(Departamento obj){return this.deptopService.save(obj);}

    @Override
    public boolean eliminar(Departamento obj) {
        try{
            this.deptopService.delete(obj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Departamento leerPorId(Integer id) {
        return this.deptopService.findById(id).orElse(new Departamento());
    }

    @Override
    public Departamento leerPorDUI(String dui) {
        return null;
    }

}
