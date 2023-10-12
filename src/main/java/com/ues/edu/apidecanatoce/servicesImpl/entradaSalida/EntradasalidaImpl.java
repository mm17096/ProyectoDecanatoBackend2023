package com.ues.edu.apidecanatoce.servicesImpl.entradaSalida;
import com.ues.edu.apidecanatoce.dtos.entradasalidaDto.EntradasalidaDto;
import com.ues.edu.apidecanatoce.dtos.entradasalidaDto.EntradasalidaPeticionDto;
import com.ues.edu.apidecanatoce.entities.departamentos.Departamento;
import com.ues.edu.apidecanatoce.entities.entradaSalida.Entrada_Salidas;
import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.entradaSalida.EntradasalidaRepository;
import com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo.ISolicitudVehiculoRepository;
import com.ues.edu.apidecanatoce.services.Ientradasalidaservice;
import com.ues.edu.apidecanatoce.servicesImpl.asignacionvale.AsignacionValeServiceImpl;
import com.ues.edu.apidecanatoce.servicesImpl.solicitudVale.SolicitudValeServiceImpl;
import com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo.SolicitudVehiculoServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EntradasalidaImpl implements Ientradasalidaservice
{
    private final EntradasalidaRepository entradasalidaRepository;
    private  final ISolicitudVehiculoRepository solicitudVehiculorepository;
    private  final AsignacionValeServiceImpl asignacionValeServiceImpl;
    private final SolicitudValeServiceImpl solicitudValeServiceImpl;
    private final SolicitudVehiculoServicesImpl solicitudvehiculoServices;



    @Override
    public EntradasalidaPeticionDto registrar(EntradasalidaDto data) {
        if(data.getEstado()==2){
            SolicitudVehiculo solicitud1=solicitudvehiculoServices.ConsinVale(data.getSolicitudvehiculo());
            if (solicitud1.isTieneVale()==true){
                //entradasalida--- solicitudvehiculo-- solicitudvale
                SolicitudVale solicitudVale= solicitudValeServiceImpl.codigosolicitudvehiculo(data.getSolicitudvehiculo());
                asignacionValeServiceImpl.actualizarEstadoEntradaSolicitud(solicitudVale.getIdSolicitudVale(),2);
                asignacionValeServiceImpl.actualizarEstadoSolicitudVehiculo(data.getSolicitudvehiculo(),5);
            }else {
                asignacionValeServiceImpl.actualizarEstadoSolicitudVehiculo(data.getSolicitudvehiculo(),7);
            }
        }else if(data.getEstado()==1){
            SolicitudVehiculo solicitud=solicitudvehiculoServices.ConsinVale(data.getSolicitudvehiculo());
            if (solicitud.isTieneVale()==true){
                SolicitudVale solicitudVale= solicitudValeServiceImpl.codigosolicitudvehiculo(data.getSolicitudvehiculo());
                asignacionValeServiceImpl.actualizarEstadoEntradaSolicitud(solicitudVale.getIdSolicitudVale(),1);
            }
        }
        return entradasalidaRepository.save(data.toEntityComplete(solicitudVehiculorepository)).toDTO();
    }

    @Override
    public EntradasalidaPeticionDto leerPorId(UUID id) {
        Entrada_Salidas entradaSalidas = entradasalidaRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentra ningun dato registrado del que desea actualizar"));
        return entradaSalidas.toDTO();
    }

    @Override
    public Page<EntradasalidaPeticionDto> listar(Pageable pageable) {
        Page<Entrada_Salidas> entradaSalidas = entradasalidaRepository.findAll(pageable);
        return entradaSalidas.map(Entrada_Salidas::toDTO);
    }

    @Override
    public List<EntradasalidaDto> listarSinPagina() {
        return null;
    }


    @Override
    public EntradasalidaDto actualizar(UUID id, EntradasalidaDto data) {
        return null;
    }

    /*@Override
    public List<EntradasalidaDto> listarSinPagina() {
        List<Entrada_Salidas> entradaSalidas= this.entradasalidaRepository.findAll();
        return entradaSalidas.stream().map(Entrada_Salidas::toDTO).toList();
    }*/

    /*@Override
    public EntradasalidaDto actualizar(UUID id, EntradasalidaDto data) {
        return entradasalidaRepository.save(data.toEntityComplete()).toDTO();
    }*/

    @Override
    public EntradasalidaDto eliminar(UUID id) {

        return null;
    }

    @Override
    public Entrada_Salidas listaEstado(int estadi, UUID id) {
        Entrada_Salidas buscar= entradasalidaRepository.findByEstadoAndSolicitudvehiculo_CodigoSolicitudVehiculo(estadi, id);
        return buscar;
    }
}
