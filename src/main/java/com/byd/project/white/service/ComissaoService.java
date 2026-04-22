package com.byd.project.white.service;


import com.byd.project.white.dto.DtoComissao;
import com.byd.project.white.model.Comissao;
import com.byd.project.white.model.Venda;
import com.byd.project.white.model.Vendedor;
import com.byd.project.white.model.enums.TipoStatus;
import com.byd.project.white.repository.ComissaoRepository;
import com.byd.project.white.repository.VendaRepository;
import com.byd.project.white.repository.VendedorRepository;
import com.byd.project.white.util.MapStruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ComissaoService {

    @Autowired
    private ComissaoRepository comissaoRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private MapStruct mapStruct;

    @Autowired
    private VendaRepository vendaRepository;

    public DtoComissao criar(DtoComissao dto) {
        Vendedor vendedor = vendedorRepository.findById(dto.getIdVendedor())
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado!"));

        Venda venda = vendaRepository.findById(dto.getIdVenda())
                .orElseThrow(() -> new RuntimeException("Venda não encontrada!"));

        Comissao comissao = mapStruct.toEntity(dto);
        comissao.setVendedor(vendedor);
        comissao.setVendaComissao(venda);

        if (comissao.getTaxa() != null && venda.getValorFinalVenda() != null) {
            BigDecimal valorComissao = venda.getValorFinalVenda().multiply(comissao.getTaxa());
            comissao.setValorComissaoFinal(valorComissao);
        }

        Comissao saved = comissaoRepository.save(comissao);
        return mapStruct.toDto(saved);
    }

    public List<DtoComissao> listarTodos(){
        return mapStruct.toDtoListComissao(comissaoRepository.findAll());
    }

    public DtoComissao buscarPorId(UUID id) {
        Comissao comissao = comissaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comissão não encontrada!"));
        return mapStruct.toDto(comissao);
    }

    public DtoComissao atualizar(UUID id, DtoComissao dto) {
        Comissao comissao = comissaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comissão não encontrada"));

        if (dto.getTaxa() != null) comissao.setTaxa(dto.getTaxa());
        if (dto.getStatus() != null) comissao.setStatus(TipoStatus.valueOf(dto.getStatus()));
        if (dto.getValorComissaoFinal() != null) comissao.setValorComissaoFinal(dto.getValorComissaoFinal());

        if (dto.getIdVendedor() != null) {
            Vendedor vendedor = vendedorRepository.findById(dto.getIdVendedor())
                    .orElseThrow(() -> new RuntimeException("Vendedor não encontrado"));
            comissao.setVendedor(vendedor);
        }
        if (dto.getIdVenda() != null) {
            Venda venda = vendaRepository.findById(dto.getIdVenda())
                    .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
            comissao.setVendaComissao(venda);
        }

        Comissao updated = comissaoRepository.save(comissao);
        return mapStruct.toDto(updated);
    }

    public void deletar(UUID id) {
        comissaoRepository.deleteById(id);
    }
}
