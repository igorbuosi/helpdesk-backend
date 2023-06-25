package com.helpdesk.helpdek.repositories;

import com.helpdesk.helpdek.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa,Integer> {
    Optional<Pessoa> findByCpf (String cpf);
    Optional<Pessoa> findByEmail (String email);
}
