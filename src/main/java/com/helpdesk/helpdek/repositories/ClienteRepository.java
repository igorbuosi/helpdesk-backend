package com.helpdesk.helpdek.repositories;

import com.helpdesk.helpdek.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
}
