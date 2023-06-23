package com.helpdesk.helpdek.services;

import com.helpdesk.helpdek.domain.Tecnico;
import com.helpdesk.helpdek.domain.dtos.TecnicoDTO;
import com.helpdesk.helpdek.repositories.TecnicoRepository;
import com.helpdesk.helpdek.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository tecnicoRepository; // se comunica com o banco de dados

    public Tecnico findById (Integer id){
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id:" + id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO objDto) {
        objDto.setId(null);
        Tecnico newObj = new Tecnico(objDto);
        return tecnicoRepository.save(newObj);
    }
}
