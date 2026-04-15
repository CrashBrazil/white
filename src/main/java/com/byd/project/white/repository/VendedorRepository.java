package com.byd.project.white.repository;

import com.byd.project.white.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VendedorRepository extends JpaRepository<Vendedor, UUID> {
}
