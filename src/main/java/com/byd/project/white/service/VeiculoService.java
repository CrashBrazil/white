package com.byd.project.white.service;

import com.byd.project.white.dto.DtoVeiculo;
import com.byd.project.white.model.Veiculo;
import com.byd.project.white.model.Venda;
import com.byd.project.white.model.enums.TipoStatusVeiculo;
import com.byd.project.white.repository.VeiculoRepository;
import com.byd.project.white.repository.VendaRepository;
import com.byd.project.white.util.MapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private MapStruct mapStruct;

    public DtoVeiculo criar(DtoVeiculo dto) {
        Veiculo veiculo = mapStruct.toEntity(dto);
        if (dto.getIdVenda() != null) {
            Venda venda = vendaRepository.findById(dto.getIdVenda())
                    .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
            veiculo.setVendaVeiculo(venda);
        }
        Veiculo saved = veiculoRepository.save(veiculo);
        return mapStruct.toDto(saved);
    }

    public List<DtoVeiculo> listarTodos() {
        return mapStruct.toDtoListVeiculo(veiculoRepository.findAll());
    }

    public DtoVeiculo buscarPorId(UUID id) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
        return mapStruct.toDto(veiculo);
    }

    public DtoVeiculo atualizar(UUID id, DtoVeiculo dto) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        if (dto.getModeloVeiculo() != null) veiculo.setModeloVeiculo(dto.getModeloVeiculo());
        if (dto.getAnoVeiculo() != null) veiculo.setAnoVeiculo(dto.getAnoVeiculo());
        if (dto.getCorVeiculo() != null) veiculo.setCorVeiculo(dto.getCorVeiculo());
        if (dto.getQuilometragem() != null) veiculo.setQuilometragem(dto.getQuilometragem());
        if (dto.getCustoVeiculo() != null) veiculo.setCustoVeiculo(dto.getCustoVeiculo());
        if (dto.getDataEntrada() != null) veiculo.setDataEntrada(dto.getDataEntrada());
        if (dto.getStatusVeiculo() != null) {
            veiculo.setStatusVeiculo(TipoStatusVeiculo.valueOf(dto.getStatusVeiculo()));
        }
        if (dto.getMarcaCarro() != null) veiculo.setMarcaCarro(dto.getMarcaCarro());
        if (dto.getPlacaCarro() != null) veiculo.setPlacaCarro(dto.getPlacaCarro());
        if (dto.getDataSaida() != null) veiculo.setDataSaida(dto.getDataSaida());
        if (dto.getIdVenda() != null) {
            Venda venda = vendaRepository.findById(dto.getIdVenda())
                    .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
            veiculo.setVendaVeiculo(venda);
        }

        Veiculo updated = veiculoRepository.save(veiculo);
        return mapStruct.toDto(updated);
    }

    public void deletar(UUID id) {
        veiculoRepository.deleteById(id);
    }
}