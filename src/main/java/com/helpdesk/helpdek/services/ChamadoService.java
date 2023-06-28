package com.helpdesk.helpdek.services;

import com.helpdesk.helpdek.domain.Chamado;
import com.helpdesk.helpdek.repositories.ChamadoRepository;
import com.helpdesk.helpdek.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    public Chamado findById(Integer id){
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("OBjeto não encontrado! ID" + id));
    }

    public List<Chamado> findAll(){
        return repository.findAll();

    }
}
