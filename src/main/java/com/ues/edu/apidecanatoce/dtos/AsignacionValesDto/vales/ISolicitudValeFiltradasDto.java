package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.vales;

import java.util.UUID;

public interface ISolicitudValeFiltradasDto {

    public UUID getCodigoSolicitudVale();

    public Integer getCantidadVales();

    public Integer getEstadoSolicitud();

    public Integer getEstadoEntradaSolicitudVale();

    public String getobservacionesSolicitudVale();

    public String getModeloVehiculo();

    public String getPlacaVehiculo();

    public UUID getCodigoSolicitudVehiculoS();

    public Integer getCantidadPersonas();

    public String getMision();

    public String getDireccionMision();

    public String getFechaSalida();

    public String getFechaEntrada();

    public String getFechaSolicitud();

    public String getUnidadSolicitante();

    public Integer getEstadoSolicitudVehiculo();

    public String getNombreSolicitante();

    public String getNombreMotorista();

    public UUID getCodigoEmpleado();

    public String getCorreoEmpleado();


}
