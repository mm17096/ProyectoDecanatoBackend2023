package com.ues.edu.apidecanatoce.servicesImpl.logVale;

import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.vales.LogValeDto;
import com.ues.edu.apidecanatoce.entities.logVale.LogVale;
import com.ues.edu.apidecanatoce.repositorys.logVale.ILogValeRepository;
import com.ues.edu.apidecanatoce.services.logVale.ILogValeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LogValeServiceImpl implements ILogValeService {

    private final ILogValeRepository logValeRepository;

    @Override
    public List<LogValeDto> obtenerLogValesPorEstadoYMes(int estado) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
        LocalDateTime lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);

        List<LogVale> logValesPorEstadoYMes = logValeRepository.findByEstadoValeAndFechaLogValeBetween(estado, firstDayOfMonth, lastDayOfMonth);

        return logValesPorEstadoYMes.stream()
                .map(LogVale::toLogValeDto)
                .collect(Collectors.toList());
    }

    @Override
    public LogVale registrar(LogVale data) {
        return null;
    }

    @Override
    public LogVale leerPorId(UUID id) {
        return null;
    }

    @Override
    public Page<LogVale> listar(Pageable pageable) {
        return null;
    }

    @Override
    public LogVale actualizar(UUID id, LogVale data) {
        return null;
    }

    @Override
    public LogVale eliminar(UUID id) {
        return null;
    }
}
