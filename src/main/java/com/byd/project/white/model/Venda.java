package com.byd.project.white.model;

import com.byd.project.white.model.enums.TipoPagamento;
import com.byd.project.white.model.enums.TipoStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
}
