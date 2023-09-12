package com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PathService {
    private Path rootPath;

    @Value("uploads")
    private String uploadsDir;

    @PostConstruct
    public void init() throws IOException {
        rootPath = Paths.get(uploadsDir);
        //podria existir multiples carpetas y se mantendria aca
        Files.createDirectories(rootPath);
    }

    public Path getPathFromUrl(String url){
        return Paths.get(url);
    }

    public String generateFileName(MultipartFile file){
        return UUID.randomUUID() + "-" + Objects.requireNonNull(file.getOriginalFilename()).replace(" ", "-");
    }

    public Path generatePath(String filename){
        return rootPath.resolve(filename).normalize().toAbsolutePath();
    }

    public void storeFile(MultipartFile file, String filename){
        /*podria haber una enumeracuin oara que carpeta subir el
        archivo como terver paametro y con base a eso se guarda el archivo*/
        try{
            if(file.isEmpty()){
                throw new RuntimeException("Filed to store empty file.");
            }
            Path destinationFile = generatePath(filename);
            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destinationFile);
            }
        }catch (Exception e){
            throw new RuntimeException("Filed to store file.*", e);
        }
    }
}