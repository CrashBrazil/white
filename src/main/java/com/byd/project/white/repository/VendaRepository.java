package com.byd.project.white.repository;

import com.byd.project.white.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface VendaRepository extends JpaRepository<Venda, UUID> {
}