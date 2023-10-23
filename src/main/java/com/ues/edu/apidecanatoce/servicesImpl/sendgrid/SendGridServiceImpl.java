package com.ues.edu.apidecanatoce.servicesImpl.sendgrid;

import com.ues.edu.apidecanatoce.dtos.sendgrid.SendGridDto;
import com.ues.edu.apidecanatoce.entities.sendgrid.Sendgrid;
import com.ues.edu.apidecanatoce.entities.usuario.Usuario;
import com.ues.edu.apidecanatoce.repositorys.sendgrid.ISendGridRepository;
import com.ues.edu.apidecanatoce.services.sendgrid.ISendGridService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendGridServiceImpl implements ISendGridService {

    private final ISendGridRepository sendGridRepository;

    public SendGridServiceImpl(ISendGridRepository sendGridRepository) {
        this.sendGridRepository = sendGridRepository;
    }
    @Override
    public Sendgrid listar() {

        List<Sendgrid> listSendgrid = this.sendGridRepository.findAll();
        Sendgrid sendgridOb = new Sendgrid();
        for (Sendgrid sendgrid : listSendgrid) {
                sendgridOb = sendgrid;
        }
        return sendgridOb;
    }
}
