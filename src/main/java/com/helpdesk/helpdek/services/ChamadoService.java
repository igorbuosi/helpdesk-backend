package com.helpdesk.helpdek.services;

import com.helpdesk.helpdek.domain.Chamado;
import com.helpdesk.helpdek.domain.Cliente;
import com.helpdesk.helpdek.domain.Tecnico;
import com.helpdesk.helpdek.domain.dtos.ChamadoDTO;
import com.helpdesk.helpdek.domain.enums.Prioridade;
import com.helpdesk.helpdek.domain.enums.Status;
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
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id){
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("OBjeto não encontrado! ID" + id));
    }

    public List<Chamado> findAll(){
        return repository.findAll();

    }

    public Chamado create(ChamadoDTO objDTO) {
        return repository.save(newChamado(objDTO));
    }

    private Chamado newChamado(ChamadoDTO obj){
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        Chamado chamado = new Chamado();
        if (obj.getId() != null){
            chamado.setId(obj.getId());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());

        return chamado;

    }
}
