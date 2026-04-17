package com.byd.project.white.service;


import com.byd.project.white.model.Comissao;
import com.byd.project.white.repository.ComissaoRepository;
import com.byd.project.white.repository.VendedorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ComissaoService {

    private final ComissaoRepository repository;
    private final VendedorRepository vendedorRepository;





}
