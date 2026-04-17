package com.byd.project.white.util;

import com.byd.project.white.dto.DtoCliente;
import com.byd.project.white.dto.DtoComissao;
import com.byd.project.white.dto.DtoVendedor;
import com.byd.project.white.model.Cliente;
import com.byd.project.white.model.Comissao;
import com.byd.project.white.model.Vendedor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapStruct {

    public DtoCliente toDto(Cliente cliente) {
        if (cliente == null) return null;

        DtoCliente dto = new DtoCliente();
        dto.setNomeCompletoCliente(cliente.getNomeCompletoCliente());
        dto.setEmailCliente(cliente.getEmailCliente());
        dto.setTelefoneCliente(cliente.getTelefoneCliente());
        dto.setSexoCliente(cliente.getSexoCliente() != null ? cliente.getSexoCliente().name() : null);
        dto.setDataNascimentoCliente(cliente.getDataNascimentoCliente());
        dto.setTipoMoradia(cliente.getTipoMoradia());
        dto.setCidade(cliente.getCidade());
        dto.setEndereco(cliente.getEndereco());
        dto.setCep(cliente.getCep());
        dto.setIdVendedor(cliente.getVendedorCliente() != null ? cliente.getVendedorCliente().getIdFuncionario() : null);

        return dto;
    }

    public Cliente toEntity(DtoCliente dto) {
        if (dto == null) return null;

        Cliente cliente = new Cliente();
        cliente.setNomeCompletoCliente(dto.getNomeCompletoCliente());
        cliente.setEmailCliente(dto.getEmailCliente());
        cliente.setTelefoneCliente(dto.getTelefoneCliente());
        cliente.setDataNascimentoCliente(dto.getDataNascimentoCliente());
        cliente.setTipoMoradia(dto.getTipoMoradia());
        cliente.setCidade(dto.getCidade());
        cliente.setEndereco(dto.getEndereco());
        cliente.setCep(dto.getCep());

        return cliente;
    }

    public DtoVendedor toDto(Vendedor vendedor) {
        if (vendedor == null) return null;

        DtoVendedor dto = new DtoVendedor();
        dto.setNomeCompleto(vendedor.getNomeCompleto());
        dto.setEndereco(vendedor.getEndereco());
        dto.setDataNascimento(vendedor.getDataNascimento());
        dto.setCpf(vendedor.getCpf());
        dto.setSexo(vendedor.getSexo());
        dto.setTelefone(vendedor.getTelefone());
        dto.setEmail(vendedor.getEmail());
        dto.setCargo(vendedor.getCargo());
        dto.setSalario(vendedor.getSalario());
        dto.setDataAdmissao(vendedor.getDataAdmissao());
        dto.setStatus(vendedor.getStatus());
        dto.setSenha(vendedor.getSenha());

        return dto;
    }

    public Vendedor toEntity(DtoVendedor dto){
        if (dto == null) return null;

        Vendedor vendedor = new Vendedor();
        vendedor.setNomeCompleto(dto.getNomeCompleto());
        vendedor.setEndereco(dto.getEndereco());
        vendedor.setDataNascimento(dto.getDataNascimento());
        vendedor.setCpf(dto.getCpf());
        vendedor.setSexo(dto.getSexo());
        vendedor.setTelefone(dto.getTelefone());
        vendedor.setEmail(dto.getEmail());
        vendedor.setCargo(dto.getCargo());
        vendedor.setSalario(dto.getSalario());
        vendedor.setDataAdmissao(dto.getDataAdmissao());
        vendedor.setStatus(dto.getStatus());
        vendedor.setSenha(dto.getSenha());

        return vendedor;
    }

    public List<DtoCliente> toDtoListCliente(List<Cliente> clientes) {
        if (clientes == null) return null;
        return clientes.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<DtoVendedor> toDtoListVendedor(List<Vendedor> vendedores) {
        return vendedores.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
