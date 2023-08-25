package com.ues.edu.apidecanatoce.services;

import com.ues.edu.apidecanatoce.entities.Documentos;


import java.util.List;

public interface IDocumentoESservice extends ICrud<Documentos> {
    List<Documentos> listarPorEstado(int estado);

}
