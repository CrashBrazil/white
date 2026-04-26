package com.byd.project.white.service;

import com.byd.project.white.dto.DtoCliente;
import com.byd.project.white.model.Cliente;
import com.byd.project.white.model.Vendedor;
import com.byd.project.white.model.enums.TipoSexo;
import com.byd.project.white.repository.ClienteRepository;
import com.byd.project.white.repository.VendedorRepository;
import com.byd.project.white.util.MapStruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    private final VendedorRepository vendedorRepository;

    private final MapStruct mapStruct;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public DtoCliente criar(DtoCliente dto) {
        Vendedor vendedor = vendedorRepository.findById(dto.getIdVendedor())
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado!"));

        Cliente cliente = mapStruct.toEntity(dto);
        cliente.setSexoCliente(TipoSexo.valueOf(dto.getSexoCliente()));
        cliente.setVendedorCliente(vendedor);

        if (dto.getSenhaCliente() != null && !dto.getSenhaCliente().isEmpty()) {
            String senhaCodificada = bCryptPasswordEncoder.encode(dto.getSenhaCliente());
            cliente.setSenhaCliente(senhaCodificada);
        } else {
            throw new RuntimeException("Senha do cliente é obrigatória");
        }

        Cliente savedCliente = repository.save(cliente);
        return mapStruct.toDto(savedCliente);
    }

    public List<DtoCliente> listar() {
        List<Cliente> clientes = repository.findAll();
        return mapStruct.toDtoListCliente(clientes);
    }

    public DtoCliente buscarPorId(UUID id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return mapStruct.toDto(cliente);
    }

    public DtoCliente atualizar(UUID id, DtoCliente dto) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));


        if (dto.getNomeCompletoCliente() != null)
            cliente.setNomeCompletoCliente(dto.getNomeCompletoCliente());
        if (dto.getSenhaCliente() != null && !dto.getSenhaCliente().isEmpty()) {
            String novaSenhaCodificada = bCryptPasswordEncoder.encode(dto.getSenhaCliente());
            cliente.setSenhaCliente(novaSenhaCodificada);
        }
        if (dto.getEmailCliente() != null)
            cliente.setEmailCliente(dto.getEmailCliente());
        if (dto.getTelefoneCliente() != null)
            cliente.setTelefoneCliente(dto.getTelefoneCliente());
        if (dto.getSexoCliente() != null)
            cliente.setSexoCliente(TipoSexo.valueOf(dto.getSexoCliente()));
        if (dto.getDataNascimentoCliente() != null)
            cliente.setDataNascimentoCliente(dto.getDataNascimentoCliente());
        if (dto.getTipoMoradia() != null)
            cliente.setTipoMoradia(dto.getTipoMoradia());
        if (dto.getCidade() != null)
            cliente.setCidade(dto.getCidade());
        if (dto.getEndereco() != null)
            cliente.setEndereco(dto.getEndereco());
        if (dto.getCep() != null)
            cliente.setCep(dto.getCep());

        Cliente updatedCliente = repository.save(cliente);
        return mapStruct.toDto(updatedCliente);
    }

    public boolean validarSenha(String senhaPlain, String senhaHash) {
        return bCryptPasswordEncoder.matches(senhaPlain, senhaHash);
    }

    public void deletar(UUID id) {
        repository.deleteById(id);
    }
}