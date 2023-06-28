package com.helpdesk.helpdek.resources;

import com.helpdesk.helpdek.domain.Cliente;
import com.helpdesk.helpdek.domain.dtos.ClienteDTO;
import com.helpdesk.helpdek.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    //localhost:8080/tecnicos/1

    @Autowired
    private ClienteService tecnicoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
        Cliente obj = tecnicoService.findById(id);
        return ResponseEntity.ok().body(new ClienteDTO(obj));

    }

    @GetMapping // sem nenhum parametro
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> list = tecnicoService.findAll();
        List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDto){
        Cliente newObj = tecnicoService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update (@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDTO){
        Cliente obj = tecnicoService.update(id, objDTO);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
        public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){
            tecnicoService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
