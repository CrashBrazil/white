package com.byd.project.white.service;


import com.byd.project.white.model.Comissao;
import com.byd.project.white.repository.ComissaoRepository;
import com.byd.project.white.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComissaoService {
    @Autowired
    private ComissaoRepository repository;

    @Autowired
    private VendedorRepository vendedorRepository;





}
