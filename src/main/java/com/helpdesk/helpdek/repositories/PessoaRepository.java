package com.helpdesk.helpdek.repositories;

import com.helpdesk.helpdek.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa,Integer> {
}
