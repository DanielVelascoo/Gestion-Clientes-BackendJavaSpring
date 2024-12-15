package com.gestion.clientes.gestion_clientes.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.clientes.gestion_clientes.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
