package com.byd.project.white.service;

import com.byd.project.white.requisicao.DtoClienteRegistrarRequisicao;
import com.byd.project.white.model.Cliente;
import com.byd.project.white.model.enums.TipoCargo;
import com.byd.project.white.repository.ClienteRepository;
import com.byd.project.white.service.impl.ClienteServiceInterface;
import com.byd.project.white.mapstruct.MapStruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
@RequiredArgsConstructor
public class ClienteService implements ClienteServiceInterface {
    public final ClienteRepository clienteRepository;
    public final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public Cliente registrar(DtoClienteRegistrarRequisicao dtoClienteRegistrarRequisicao) throws SQLIntegrityConstraintViolationException {
        Cliente cliente = MapStruct.INSTANCE.converterparaCliente(dtoClienteRegistrarRequisicao);
        String encriptacao = bCryptPasswordEncoder.encode(cliente.getSenhaCliente());

        if(clienteRepository.findByEmailCliente(cliente.getEmailCliente()).isPresent()){
            throw new SQLIntegrityConstraintViolationException();
        }
        else if(dtoClienteRegistrarRequisicao != null && cliente.getSenhaCliente().length()>7){
            cliente.setSenhaCliente(encriptacao);
            cliente.setCargoCliente(TipoCargo.CLIENTE);
            return clienteRepository.save(cliente);
        }
        return null;
    }



}
