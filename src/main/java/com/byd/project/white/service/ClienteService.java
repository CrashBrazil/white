package com.byd.project.white.service;

import com.byd.project.white.dto.DtoCliente;
import com.byd.project.white.model.Cliente;
import com.byd.project.white.repository.ClienteRepository;
import com.byd.project.white.service.impl.ClienteServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService implements ClienteServiceInterface {
    public final ClienteRepository clienteRepository;
    public final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public Cliente registrar(DtoCliente dtoCliente) {

    }
}
