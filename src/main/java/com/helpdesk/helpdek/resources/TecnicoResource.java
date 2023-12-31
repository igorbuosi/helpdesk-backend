package com.helpdesk.helpdek.resources;

import com.helpdesk.helpdek.domain.Tecnico;
import com.helpdesk.helpdek.domain.dtos.TecnicoDTO;
import com.helpdesk.helpdek.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    //localhost:8080/tecnicos/1

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        Tecnico obj = tecnicoService.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));

    }

    @GetMapping // sem nenhum parametro
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        List<Tecnico> list = tecnicoService.findAll();
        List<TecnicoDTO> listDto = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDto){
        Tecnico newObj = tecnicoService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update (@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDTO){
        Tecnico obj = tecnicoService.update(id, objDTO);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
        public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id){
            tecnicoService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
