package com.ues.edu.apidecanatoce.services.documentoVale;

import com.ues.edu.apidecanatoce.entities.documentoVale.Documentovale;
import com.ues.edu.apidecanatoce.repositorys.documentoVale.ArchivoRepository;
import com.ues.edu.apidecanatoce.services.PathService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class ArchivoService {

    private final ArchivoRepository archivorepository;
    private final PathService pathService;

    public Resource downloadFile(String filename){
        System.out.println(filename);
        Documentovale archivo= archivorepository.findByFoto(filename).orElseThrow(()-> new RuntimeException("Archivo no encontrado" + filename));
        Path pathFile= pathService.getPathFromUrl(archivo.getUrl());
        Resource resource;
        try {
            resource= new UrlResource(pathFile.toUri());
            if (!resource.exists() || !resource.isReadable()){
                throw  new RuntimeException("could not read the file");
            }

        }catch (Exception e){
            throw new RuntimeException("Error: " + e.getMessage());
        }
        return resource;
    }

}
