package com.helpdesk.helpdek.services;

import com.helpdesk.helpdek.domain.Chamado;
import com.helpdesk.helpdek.domain.Cliente;
import com.helpdesk.helpdek.domain.Tecnico;
import com.helpdesk.helpdek.domain.enums.Perfil;
import com.helpdesk.helpdek.domain.enums.Prioridade;
import com.helpdesk.helpdek.domain.enums.Status;
import com.helpdesk.helpdek.repositories.ChamadoRepository;
import com.helpdesk.helpdek.repositories.ClienteRepository;
import com.helpdesk.helpdek.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {
    @Autowired // injeção de dependencia
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB(){
        Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "45647242820","valdir@email.com", "123456");
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Cliente", "21284267822","teste@hotmail.com.br","321");

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO,"Chamado 01","Primeiro Chamado",tec1,cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(c1));
    }
}
