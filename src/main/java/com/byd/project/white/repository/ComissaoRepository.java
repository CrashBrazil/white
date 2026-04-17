package com.byd.project.white.repository;

import com.byd.project.white.model.Comissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ComissaoRepository extends JpaRepository<Comissao, UUID> {

}
