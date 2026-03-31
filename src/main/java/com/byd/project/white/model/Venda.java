package com.byd.project.white.model;

import com.byd.project.white.model.enums.TipoPagamento;
import com.byd.project.white.model.enums.TipoStatus;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idVenda;

    @NotNull
    private String dataVenda;

    @NotNull
    private String veiculoVendido;

    @NotNull
    private String vendedorResponsavelVenda;

    @NotNull
    private BigDecimal valorVenda;

    private BigDecimal descontoVenda;

    @NotNull
    private BigDecimal valorFinalVenda;

    @NotNull
    private TipoStatus statusVenda;

    @NotNull
    private TipoPagamento tipoPagamento;

    @ManyToMany(mappedBy = "vendas", fetch = FetchType.LAZY)
    private List<Vendedor> vendedores;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVendaCliente", nullable = false)
    private Cliente ClienteVenda;

    @OneToMany(mappedBy = "vendaVeiculo")
    private List<Veiculo> veiculosVenda;


}
