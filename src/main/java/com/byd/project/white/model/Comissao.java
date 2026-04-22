package com.byd.project.white.model;

import com.byd.project.white.model.enums.TipoStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comissao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idComissao;

    @NotNull
    private BigDecimal taxa;

    @NotNull
    private TipoStatus status;

    private BigDecimal valorComissaoFinal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVendedor", nullable = false)
    private Vendedor vendedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "idVenda", nullable = false)
    private Venda vendaComissao;

}
