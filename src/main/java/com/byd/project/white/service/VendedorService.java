package com.byd.project.white.service;

import com.byd.project.white.dto.DtoVendedor;
import com.byd.project.white.model.Vendedor;
import com.byd.project.white.repository.VendedorRepository;
import com.byd.project.white.util.MapStruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class VendedorService {

    private final VendedorRepository vendedorRepository;

    private final MapStruct mapStruct;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public DtoVendedor criar(DtoVendedor dto) {
        Vendedor vendedor = mapStruct.toEntity(dto);

        String senhaCodificada = passwordEncoder.encode(dto.getSenha());
        vendedor.setSenha(senhaCodificada);

        Vendedor savedVendedor = vendedorRepository.save(vendedor);
        return mapStruct.toDto(savedVendedor);
    }

    public List<DtoVendedor> listarTodos() {
        return mapStruct.toDtoListVendedor(vendedorRepository.findAll());
    }

    public DtoVendedor buscarPorId(UUID id) {
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado"));
        return mapStruct.toDto(vendedor);
    }

    public DtoVendedor atualizar(UUID id, DtoVendedor dto) {
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado"));

        if (dto.getNomeCompleto() != null) vendedor.setNomeCompleto(dto.getNomeCompleto());
        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            String novaSenhaCodificada = passwordEncoder.encode(dto.getSenha());
            vendedor.setSenha(novaSenhaCodificada);
        }
        if (dto.getEndereco() != null) vendedor.setEndereco(dto.getEndereco());
        if (dto.getTelefone() != null) vendedor.setTelefone(dto.getTelefone());
        if (dto.getEmail() != null) vendedor.setEmail(dto.getEmail());
        if (dto.getSalario() != null) vendedor.setSalario(dto.getSalario());
        if (dto.getStatus() != null) vendedor.setStatus(dto.getStatus());

        Vendedor updatedVendedor = vendedorRepository.save(vendedor);
        return mapStruct.toDto(updatedVendedor);
    }

    public boolean validarSenha(String senhaPlain, String senhaHash) {
        return passwordEncoder.matches(senhaPlain, senhaHash);
    }
    public void deletar(UUID id) {
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado"));
        vendedorRepository.delete(vendedor);
    }
}