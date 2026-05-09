package com.byd.project.white.mapstruct;

import com.byd.project.white.dto.*;
import com.byd.project.white.model.*;
import com.byd.project.white.model.enums.TipoStatus;
import com.byd.project.white.model.enums.TipoStatusVeiculo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import com.byd.project.white.model.*;

@Component
public class MapManual {

    public DtoCliente toDto(Cliente cliente) {
            if (cliente == null) return null;

            DtoCliente dto = new DtoCliente();
            dto.setNomeCompletoCliente(cliente.getNomeCompletoCliente());
            dto.setSenhaCliente(cliente.getSenhaCliente());
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
            cliente.setSenhaCliente(dto.getSenhaCliente());
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

        public DtoComissao toDto(Comissao comissao) {
            if (comissao == null) return null;

            DtoComissao dto = new DtoComissao();
            dto.setIdComissao(comissao.getIdComissao());
            dto.setTaxa(comissao.getTaxa());
            dto.setStatus(comissao.getStatus() != null ? comissao.getStatus().name() : null);
            dto.setValorComissaoFinal(comissao.getValorComissaoFinal());
            dto.setIdVendedor(comissao.getVendedor() != null ? comissao.getVendedor().getIdFuncionario() : null);
            dto.setIdVenda(comissao.getVendaComissao() != null ? comissao.getVendaComissao().getIdVenda() : null);

            return dto;
        }

        public Comissao toEntity(DtoComissao dto) {
            if (dto == null) return null;

            Comissao comissao = new Comissao();
            comissao.setIdComissao(dto.getIdComissao());
            comissao.setTaxa(dto.getTaxa());
            if (dto.getStatus() != null) {
                comissao.setStatus(TipoStatus.valueOf(dto.getStatus()));
            }
            comissao.setValorComissaoFinal(dto.getValorComissaoFinal());

            return comissao;
        }

        public DtoVenda toDto(Venda venda) {
            if (venda == null) return null;
            DtoVenda dto = new DtoVenda();
            dto.setIdVenda(venda.getIdVenda());
            dto.setDataVenda(venda.getDataVenda());
            dto.setVeiculoVendido(venda.getVeiculoVendido());
            dto.setIdVendedor(venda.getVendedorVenda() != null ? venda.getVendedorVenda().getIdFuncionario() : null);
            dto.setValorVenda(venda.getValorVenda());
            dto.setDescontoVenda(venda.getDescontoVenda());
            dto.setValorFinalVenda(venda.getValorFinalVenda());
            dto.setStatusVenda(venda.getStatusVenda() != null ? venda.getStatusVenda().name() : null);
            dto.setTipoPagamento(venda.getTipoPagamento() != null ? venda.getTipoPagamento().name() : null);
            dto.setIdCliente(venda.getClienteVenda() != null ? venda.getClienteVenda().getIdCliente() : null);

            return dto;
        }

        public Venda toEntity(DtoVenda dto) {
            if (dto == null) return null;
            Venda venda = new Venda();
            venda.setIdVenda(dto.getIdVenda());
            venda.setDataVenda(dto.getDataVenda());
            venda.setVeiculoVendido(dto.getVeiculoVendido());
            venda.setValorVenda(dto.getValorVenda());
            venda.setDescontoVenda(dto.getDescontoVenda());
            venda.setValorFinalVenda(dto.getValorFinalVenda());

            return venda;
        }

        public DtoVeiculo toDto(Veiculo veiculo) {
            if (veiculo == null) return null;
            DtoVeiculo dto = new DtoVeiculo();
            dto.setIdVeiculo(veiculo.getIdVeiculo());
            dto.setModeloVeiculo(veiculo.getModeloVeiculo());
            dto.setAnoVeiculo(veiculo.getAnoVeiculo());
            dto.setCorVeiculo(veiculo.getCorVeiculo());
            dto.setQuilometragem(veiculo.getQuilometragem());
            dto.setCustoVeiculo(veiculo.getCustoVeiculo());
            dto.setDataEntrada(veiculo.getDataEntrada());
            dto.setStatusVeiculo(veiculo.getStatusVeiculo() != null ? veiculo.getStatusVeiculo().name() : null);
            dto.setMarcaCarro(veiculo.getMarcaCarro());
            dto.setPlacaCarro(veiculo.getPlacaCarro());
            dto.setDataSaida(veiculo.getDataSaida());
            dto.setIdVenda(veiculo.getVendaVeiculo() != null ? veiculo.getVendaVeiculo().getIdVenda() : null);
            return dto;
        }

        public Veiculo toEntity(DtoVeiculo dto) {
            if (dto == null) return null;
            Veiculo veiculo = new Veiculo();
            veiculo.setIdVeiculo(dto.getIdVeiculo());
            veiculo.setModeloVeiculo(dto.getModeloVeiculo());
            veiculo.setAnoVeiculo(dto.getAnoVeiculo());
            veiculo.setCorVeiculo(dto.getCorVeiculo());
            veiculo.setQuilometragem(dto.getQuilometragem());
            veiculo.setCustoVeiculo(dto.getCustoVeiculo());
            veiculo.setDataEntrada(dto.getDataEntrada());
            if (dto.getStatusVeiculo() != null) {
                veiculo.setStatusVeiculo(TipoStatusVeiculo.valueOf(dto.getStatusVeiculo()));
            }
            veiculo.setMarcaCarro(dto.getMarcaCarro());
            veiculo.setPlacaCarro(dto.getPlacaCarro());
            veiculo.setDataSaida(dto.getDataSaida());

            return veiculo;
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

        public List<DtoComissao> toDtoListComissao(List<Comissao> comissoes) {
            if (comissoes == null) return null;
            return comissoes.stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
        }

        public List<DtoVenda> toDtoListVenda(List<Venda> vendas) {
            if (vendas == null) return null;
            return vendas.stream().map(this::toDto).collect(Collectors.toList());
        }

        public List<DtoVeiculo> toDtoListVeiculo(List<Veiculo> veiculos) {
            if (veiculos == null) return null;
            return veiculos.stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
        }
    }


