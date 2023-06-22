package com.helpdesk.helpdek.repositories;

import com.helpdesk.helpdek.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado,Integer> {
}
