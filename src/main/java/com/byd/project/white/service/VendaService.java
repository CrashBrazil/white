package com.byd.project.white.service;

import com.byd.project.white.dto.DtoVenda;
import com.byd.project.white.mapstruct.MapManual;
import com.byd.project.white.model.Cliente;
import com.byd.project.white.model.Venda;
import com.byd.project.white.model.Vendedor;
import com.byd.project.white.model.enums.TipoPagamento;
import com.byd.project.white.model.enums.TipoStatus;
import com.byd.project.white.repository.ClienteRepository;
import com.byd.project.white.repository.VendaRepository;
import com.byd.project.white.repository.VendedorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;
    private final VendedorRepository vendedorRepository;
    private final ClienteRepository clienteRepository;
    private final MapManual mapManual;

    public DtoVenda criar(DtoVenda dto) {
        Vendedor vendedor = vendedorRepository.findById(dto.getIdVendedor())
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado!"));
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("cliente não encontrado!"));

        Venda venda = mapManual.toEntity(dto);
        venda.setVendedorVenda(vendedor);
        venda.setClienteVenda(cliente);

        if (dto.getStatusVenda() != null)
            venda.setStatusVenda(TipoStatus.valueOf(dto.getStatusVenda()));
        if (dto.getTipoPagamento() != null)
            venda.setTipoPagamento(TipoPagamento.valueOf(dto.getTipoPagamento()));

        if (venda.getValorFinalVenda() == null && venda.getValorVenda() != null) {
            BigDecimal desconto = venda.getDescontoVenda() != null ? venda.getDescontoVenda() : BigDecimal.ZERO;
            venda.setValorVenda(venda.getValorVenda().subtract(desconto));
        }

        Venda saved = vendaRepository.save(venda);
        return mapManual.toDto(saved);
    }

    public List<DtoVenda> listarTodas() {
        return mapManual.toDtoListVenda(vendaRepository.findAll());
    }

    public DtoVenda buscarPorId(UUID id) {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
        return mapManual.toDto(venda);
    }

    public DtoVenda atualizar(UUID id, DtoVenda dto) {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        if (dto.getVeiculoVendido() != null) venda.setVeiculoVendido(dto.getVeiculoVendido());
        if (dto.getValorVenda() != null) venda.setValorVenda(dto.getValorVenda());
        if (dto.getDescontoVenda() != null) venda.setDescontoVenda(dto.getDescontoVenda());
        if (dto.getValorFinalVenda() != null) venda.setValorFinalVenda(dto.getValorFinalVenda());
        if (dto.getStatusVenda() != null) venda.setStatusVenda(TipoStatus.valueOf(dto.getStatusVenda()));
        if (dto.getTipoPagamento() != null) venda.setTipoPagamento(TipoPagamento.valueOf(dto.getTipoPagamento()));

        if (dto.getIdVendedor() != null && !dto.getIdVendedor().equals(venda.getVendedorVenda().getIdFuncionario())) {
            Vendedor vendedor = vendedorRepository.findById(dto.getIdVendedor())
                    .orElseThrow(() -> new RuntimeException("Vendedor não encontrado"));
            venda.setVendedorVenda(vendedor);
        }
        if (dto.getIdCliente() != null && !dto.getIdCliente().equals(venda.getClienteVenda().getIdCliente())) {
            Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
            venda.setClienteVenda(cliente);
        }

        Venda updated = vendaRepository.save(venda);
        return mapManual.toDto(updated);
    }

    public void deletar(UUID id) {
        vendaRepository.deleteById(id);
    }
}