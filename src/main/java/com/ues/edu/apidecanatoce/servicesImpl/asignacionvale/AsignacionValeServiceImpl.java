package com.ues.edu.apidecanatoce.servicesImpl.asignacionvale;

import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.AsignacionValeDto;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.AsignacionValeInDto;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.AsignacionValeOutDto;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.DetalleAsignacionDto;
import com.ues.edu.apidecanatoce.entities.AsignacionVales.AsignacionVale;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.asignacionvale.IAsignacionValeRepository;
import com.ues.edu.apidecanatoce.repositorys.asignacionvale.IDetalleAsignacionRepository;
import com.ues.edu.apidecanatoce.repositorys.asignacionvale.ISolicitudValeRepository;
import com.ues.edu.apidecanatoce.services.asignacionvale.IAsignacionValeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AsignacionValeServiceImpl implements IAsignacionValeService {

    private final IAsignacionValeRepository asignacionValeRepository;

    private final IDetalleAsignacionRepository detalleAsignacionRepository;

    private final ISolicitudValeRepository solicitudValeRepository;

    @Override
    public AsignacionValeInDto registrar(AsignacionValeInDto data) {




        // Guardando la Asignación
        try{
            this.asignacionValeRepository.save(data.toAsignacionValeComplete(solicitudValeRepository)).toAsignacionValeDTO();
        }catch (Exception e){
            throw new CustomException(HttpStatus.BAD_REQUEST, "No se pudo guardar la asignación");
        }


        //Listar cantidad de vales segun el número solicitado


        // Obteniendo la ültima asignación reistrada

        // Guardar en la tabla detalle_asignación los id de los vales junto con la ültima asignación reistrada

        //Cambiar el estado del Vale,


        // Retornar el mensaje que toso se guardó
        return null;
    }

    @Override
    public AsignacionValeDto leerPorId(UUID id) {
        return null;
    }

    @Override
    public Page<AsignacionValeDto> listar(Pageable pageable) {
        return null;
    }

    @Override
    public AsignacionValeDto actualizar(UUID id, AsignacionValeDto data) {
        return null;
    }

    @Override
    public AsignacionValeDto eliminar(UUID id) {
        return null;
    }

    @Override
    public AsignacionValeOutDto verAsignacionesById(UUID id) {

        AsignacionVale asignacionVale = this.asignacionValeRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro la asignacion"));
        return asignacionVale.toDto(asignacionValeRepository, detalleAsignacionRepository, id);
    }

    @Override
    public DetalleAsignacionDto verDetalleById(UUID id) {
        return null;
    }

}
