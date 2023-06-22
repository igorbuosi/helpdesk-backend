package com.helpdesk.helpdek.services;

import com.helpdesk.helpdek.domain.Tecnico;
import com.helpdesk.helpdek.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository tecnicoRepository; // se comunica com o banco de dados

    public Tecnico findById (Integer id){
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElse(null);
    }

}
