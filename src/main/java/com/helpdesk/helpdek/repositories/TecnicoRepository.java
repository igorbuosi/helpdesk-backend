package com.helpdesk.helpdek.repositories;

import com.helpdesk.helpdek.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico,Integer> {
}
