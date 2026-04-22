package com.byd.project.white.model;

import com.byd.project.white.model.enums.TipoPagamento;
import com.byd.project.white.model.enums.TipoStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idVenda;

    @NotNull
    private LocalDateTime dataVenda;

    @NotNull
    private String veiculoVendido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVendedor", nullable = false)
    private Vendedor vendedorVenda;

    @NotNull
    private BigDecimal valorVenda;

    private BigDecimal descontoVenda;

    @NotNull
    private BigDecimal valorFinalVenda;

    @NotNull
    private TipoStatus statusVenda;

    @NotNull
    private TipoPagamento tipoPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente clienteVenda;

    //@OneToMany(mappedBy = "vendaVeiculo")
    //private List<Veiculo> veiculosVenda;

}