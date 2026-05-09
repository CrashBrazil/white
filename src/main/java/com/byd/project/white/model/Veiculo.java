package com.byd.project.white.model;

import com.byd.project.white.model.enums.TipoStatus;
import com.byd.project.white.model.enums.TipoStatusVeiculo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idVeiculo;

    @NotNull
    @Column(length = 20)
    private String modeloVeiculo;

    @NotNull
    private Integer anoVeiculo;

    @NotNull
    @Column(length = 12)
    private String corVeiculo;

    @NotNull
    @Column(length = 20)
    private Long quilometragem;

    @NotNull
    private BigDecimal custoVeiculo;

    @NotNull
    private Date dataEntrada;

    @NotNull
    private TipoStatusVeiculo statusVeiculo;

    @NotNull
    @Column(length = 12)
    private String marcaCarro;

    @NotNull
    @Column(length = 7, unique = true)
    private String placaCarro;

    private Date dataSaida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venda")
    private Venda vendaVeiculo;
}
