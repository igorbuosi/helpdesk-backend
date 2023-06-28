package com.helpdesk.helpdek.services;

import com.helpdesk.helpdek.domain.Cliente;
import com.helpdesk.helpdek.domain.Pessoa;
import com.helpdesk.helpdek.domain.dtos.ClienteDTO;
import com.helpdesk.helpdek.repositories.ClienteRepository;
import com.helpdesk.helpdek.repositories.PessoaRepository;
import com.helpdesk.helpdek.services.exceptions.DataIntegrityViolationException;
import com.helpdesk.helpdek.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository; // se comunica com o banco de dados

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById (Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id:" + id));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDTO objDto) {
        objDto.setId(null);
        validaporCpfEEmail(objDto);
        Cliente newObj = new Cliente(objDto);
        return clienteRepository.save(newObj);
    }

    public Cliente update(Integer id, ClienteDTO objDTO) {
        objDTO.setId(id);
        Cliente oldObj = findById(id);
        validaporCpfEEmail(objDTO);
        oldObj = new Cliente(objDTO);
        return clienteRepository.save(oldObj);

    }

    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (obj.getChamados().size() > 0 ){
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
        }

        clienteRepository.deleteById(id);
    }

    private void validaporCpfEEmail(ClienteDTO objDto) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());

        if (obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }

        obj = pessoaRepository.findByEmail(objDto.getEmail());

        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema");
        }
    }



}
