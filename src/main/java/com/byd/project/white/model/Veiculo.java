package com.byd.project.white.model;

import com.byd.project.white.model.enums.TipoStatus;
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
    private Date anoVeiculo;

    @NotNull
    @Column(length = 12)
    private String corVeiculo;

    @NotNull
    @Column(length = 20)
    private String quilomentragem;

    @NotNull
    private BigDecimal custoVeiculo;

    @NotNull
    private Date dataEntrada;

    @NotNull
    private TipoStatus statusVendaVeiculo;

    @NotNull
    @Column(length = 12)
    private String marcaCarro;

    @NotNull
    @Column(length = 7)
    private String placaCarro;

    private Date dataSaida;
}
