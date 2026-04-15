package com.byd.project.white.repository;

import com.byd.project.white.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByClientId(String clientid);
}
