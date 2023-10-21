package com.ues.edu.apidecanatoce.repositorys.solicitudVale;

import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaSoliValeIdDto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaSolisValeIdDto;
import com.ues.edu.apidecanatoce.entities.documentoVale.Documentovale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IConsultaDocumentValeRepository extends JpaRepository<Documentovale, UUID> {
    @Query(value = "SELECT doc.codigodocumentos AS codigodocumentos, doc.comprobante AS comprobante, doc.fecha AS fecha, doc.foto AS foto, doc.tipo AS tipo, doc.url AS url, doc.id_solicitud_vale AS idsolicitudvale FROM tb_documentosvale doc WHERE doc.id_solicitud_vale = :id ORDER BY doc.fecha ASC", nativeQuery = true)
    List<ConsultaSoliValeIdDto> listarDocumentoSoliVale(UUID id);

    @Query(value = "SELECT sv.id_solicitud_vale AS idsolicitudvale, sv.estado AS estado FROM tb_solicitud_vale sv WHERE sv.solicitud_vehiculo_id = :id", nativeQuery = true)
    List<ConsultaSolisValeIdDto> listarDocumentoSoliValeid(UUID id);
}
