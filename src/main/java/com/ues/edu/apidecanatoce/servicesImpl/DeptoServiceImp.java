package com.ues.edu.apidecanatoce.servicesImpl;

import com.ues.edu.apidecanatoce.dtos.CargosDto.CargosDto;
import com.ues.edu.apidecanatoce.dtos.DepartamentoDto.DepartamentoDto;
import com.ues.edu.apidecanatoce.entities.Cargos.Cargo;
import com.ues.edu.apidecanatoce.entities.Departamentos.Departamento;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.IDeptopRepo;
import com.ues.edu.apidecanatoce.services.IDeptoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DeptoServiceImp implements IDeptoService {

    private final IDeptopRepo deptopRepository;

    @Override
    public List<DepartamentoDto> listar(){
        List<Departamento> listDepto = this.deptopRepository.findAll();
        return listDepto.stream().map(Departamento::toDto).toList();
    }

    @Override
    public DepartamentoDto registrar(DepartamentoDto data){
        if (this.deptopRepository.existsByNombre(data.getNombre())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El Cargo ya está registrado");
        }
        return this.deptopRepository.save(data.toEntityComplete()).toDto();
    }

    @Override
    public DepartamentoDto modificar(DepartamentoDto data){
        if (this.deptopRepository.existsByNombre(data.getNombre())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El Cargo ya está registrado");
        }
        return this.deptopRepository.save(data.toEntityComplete()).toDto();
    }

    @Override
    public DepartamentoDto leerPorId(UUID id) {
        Departamento depto = deptopRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentra el cargo"));
        return depto.toDto();
    }

    @Override
    public DepartamentoDto eliminar(UUID id) {
        DepartamentoDto depto = leerPorId(id);
        deptopRepository.delete(depto.toEntityComplete());
        return depto;
    }

    @Override
    public Page<DepartamentoDto> listarConPage(Pageable pageable) {
        Page<Departamento> deptos = deptopRepository.findAll(pageable);
        return deptos.map(Departamento::toDto);
    }

    @Override
    public List<Departamento> findAllByEstado(int estado){ return  this.deptopRepository.findAllByEstado(estado);}
}
