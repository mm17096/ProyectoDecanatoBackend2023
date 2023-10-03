package com.ues.edu.apidecanatoce.repositorys.solicitudVale;

import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaCompraDto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaDocumentSoliCarDto;
import com.ues.edu.apidecanatoce.entities.compras.Compra;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.DocumentoSoliCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Repository
public interface IConsultaDocumentSoliCarRepository extends JpaRepository<DocumentoSoliCar, UUID> {
    @Query(value = "SELECT ds.codigo_documento AS codigodocumento, ds.fecha AS fecha, ds.nombredocment AS nombredocment, ds.urldocument AS urldocument, ds.codigo_solicitud_vehiculo AS codigosolicitudvehiculo, ds.tipodocument AS tipodocument FROM tb_documentosolicar ds WHERE ds.codigo_solicitud_vehiculo = :id ORDER BY ds.fecha ASC", nativeQuery = true)
    List<ConsultaDocumentSoliCarDto> listarDocumentoSoli(UUID id);
}
