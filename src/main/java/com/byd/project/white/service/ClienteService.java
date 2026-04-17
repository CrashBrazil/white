package com.byd.project.white.service;

import com.byd.project.white.mapstruct.Conversor;
import com.byd.project.white.model.Vendedor;
import com.byd.project.white.model.enums.TipoSexo;
import com.byd.project.white.repository.VendedorRepository;
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
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;
    private final VendedorRepository vendedorRepository;
    private final Conversor conversor;


    public DtoClienteRegistrarRequisicao criar(DtoClienteRegistrarRequisicao dto) {
        Vendedor vendedor = vendedorRepository.findById(dto.getIdVendedor())
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado!"));
        Cliente cliente = conversor.toEntity(dto);
        cliente.setSexoCliente(TipoSexo.valueOf(dto.getSexoCliente()));
        cliente.setVendedorCliente(vendedor);

        Cliente savedCliente = repository.save(cliente);
        return conversor.toDto(savedCliente);
    }

    public List<DtoClienteRegistrarRequisicao> listar() {
        List<Cliente> clientes = repository.findAll();
        return conversor.toDtoListCliente(clientes);
    }

    public DtoClienteRegistrarRequisicao buscarPorId(UUID id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return conversor.toDto(cliente);
    }

    public DtoClienteRegistrarRequisicao atualizar(UUID id, DtoClienteRegistrarRequisicao dto) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));


        if (dto.getNomeCompletoCliente() != null)
            cliente.setNomeCompletoCliente(dto.getNomeCompletoCliente());
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
        return conversor.toDto(updatedCliente);
    }


    public void deletar(UUID id) {
        repository.deleteById(id);
    }







}
