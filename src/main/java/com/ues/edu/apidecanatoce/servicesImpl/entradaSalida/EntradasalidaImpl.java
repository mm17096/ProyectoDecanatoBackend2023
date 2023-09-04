package com.ues.edu.apidecanatoce.servicesImpl.entradaSalida;
import com.ues.edu.apidecanatoce.dtos.entradasalidaDto.EntradasalidaDto;
import com.ues.edu.apidecanatoce.entities.entradaSalida.Entrada_Salidas;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.entradaSalida.EntradasalidaRepository;
import com.ues.edu.apidecanatoce.services.Ientradasalidaservice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EntradasalidaImpl implements Ientradasalidaservice {
    private final EntradasalidaRepository entradasalidaRepository;


    @Override
    public EntradasalidaDto registrar(EntradasalidaDto data) {
        return entradasalidaRepository.save(data.toEntityComplete()).toDTO();
    }

    @Override
    public EntradasalidaDto leerPorId(UUID id) {
        Entrada_Salidas entradaSalidas = entradasalidaRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentra ningun dato registrado del que desea actualizar"));
        return entradaSalidas.toDTO();
    }

    @Override
    public Page<EntradasalidaDto> listar(Pageable pageable) {
        Page<Entrada_Salidas> entradaSalidas = entradasalidaRepository.findAll(pageable);
        return entradaSalidas.map(Entrada_Salidas::toDTO);
    }

    @Override
    public List<EntradasalidaDto> listarSinPagina() {
        List<Entrada_Salidas> entradaSalidas= this.entradasalidaRepository.findAll();
        return entradaSalidas.stream().map(Entrada_Salidas::toDTO).toList();
    }

    @Override
    public EntradasalidaDto actualizar(UUID id, EntradasalidaDto data) {
        EntradasalidaDto buscar = leerPorId(id);
        data.setId(id);
        return entradasalidaRepository.save(data.toEntityComplete()).toDTO();
    }

    @Override
    public EntradasalidaDto eliminar(UUID id) {

        return null;
    }
}
