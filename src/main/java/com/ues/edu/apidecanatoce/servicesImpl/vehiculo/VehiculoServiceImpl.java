package com.ues.edu.apidecanatoce.servicesImpl.vehiculo;


import com.ues.edu.apidecanatoce.dtos.MensajeRecord;
import com.ues.edu.apidecanatoce.dtos.vehiculo.VehiculoDto;
import com.ues.edu.apidecanatoce.entities.vehiculo.Vehiculo;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.vehiculo.IVehiculoRepository;
import com.ues.edu.apidecanatoce.services.PathService;
import com.ues.edu.apidecanatoce.services.vehiculo.IVehiculoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehiculoServiceImpl implements IVehiculoService {

    @Autowired
    private IVehiculoRepository vehiculoRepository;

    private final PathService pathService;
    private final HttpServletRequest request;

    @Override
    public MensajeRecord registrar(MultipartFile imagen, VehiculoDto data) {
        if (vehiculoRepository.existsByPlacaIgnoreCase(data.getPlaca())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El número de placa ya está registrado");
        }
        if (imagen != null && !imagen.isEmpty()) {
            try {
                // Guardar la imagen en la carpeta del proyecto
                String filename = pathService.generateFileName(imagen);
                pathService.storeFile(imagen, filename);
                Path destinationFile = pathService.generatePath(filename);
                Files.write(destinationFile, imagen.getBytes());

                // Guardar la URL de la imagen en el campo urlfoto y el nombre en nombrefoto del vehículo
                data.setNombrefoto(generateUrlImage(filename));
                data.setUrlfoto(destinationFile.toString());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        vehiculoRepository.save(data.toEntity());

        return new MensajeRecord("exito se guardo");
    }

    public String generateUrlImage(String imageName) {
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        return host + "/api/vehiculo/imagen/" + imageName;
    }

    @Override
    public VehiculoDto leerPorId(UUID id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentra vehiculo"));
        return vehiculo.toDTO();
    }

    @Override
    public Page<VehiculoDto> listar(Pageable pageable) {
        Page<Vehiculo> vehiculos = vehiculoRepository.findAll(pageable);
        return vehiculos.map(Vehiculo::toDTO);
    }

    @Override
    public List<VehiculoDto> listarSinPagina() {
        List<Vehiculo> vehiculoList= this.vehiculoRepository.findAll();
        return vehiculoList.stream().map(Vehiculo::toDTO).toList();
    }

    @Override
    public List<VehiculoDto> listarPorPlaca(String codigoplaca) {
        List<Vehiculo> vehiculos = this.vehiculoRepository.findByPlacaIgnoreCase(codigoplaca);
        return vehiculos.stream().map(Vehiculo::toDTO).toList();
    }

    @Override
    public List<VehiculoDto> listarPorClase(String nombreClase) {
        List<Vehiculo> vehiculos = this.vehiculoRepository.findByClaseIgnoreCase(nombreClase);
        return vehiculos.stream().map(Vehiculo::toDTO).toList();
    }

    @Override
    public MensajeRecord actualizar(MultipartFile imagen,VehiculoDto data) {
        VehiculoDto buscarProveedor = leerPorId(data.getCodigoVehiculo());
        if (vehiculoRepository.existsByPlacaAndCodigoVehiculoNot(data.getPlaca(), data.getCodigoVehiculo())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "La placa ya está registrado");
        }
        if (imagen != null && !imagen.isEmpty()) {
            try {
                // Guardar la imagen en la carpeta del proyecto
                String filename = pathService.generateFileName(imagen);
                pathService.storeFile(imagen, filename);
                Path destinationFile = pathService.generatePath(filename);
                Files.write(destinationFile, imagen.getBytes());

                // Guardar la URL de la imagen en el campo urlfoto y el nombre en nombrefoto del vehículo
                data.setNombrefoto(generateUrlImage(filename));;
                data.setUrlfoto(destinationFile.toString());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        vehiculoRepository.save(data.toEntity());
        return new MensajeRecord("exito se edito");
    }

    @Override
    public VehiculoDto eliminar(UUID id) {
        VehiculoDto vehiculoDto = leerPorId(id);
        vehiculoRepository.delete(vehiculoDto.toEntity());
        return vehiculoDto;
    }
}
