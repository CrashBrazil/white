package com.byd.project.white.repository;

import com.byd.project.white.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByEmailCliente(String email);


}
