package com.byd.project.white.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Data
public class DtoVeiculo {
    private UUID idVeiculo;
    private String modeloVeiculo;
    private Integer anoVeiculo;
    private String corVeiculo;
    private Long quilometragem;
    private BigDecimal custoVeiculo;
    private Date dataEntrada;
    private String statusVeiculo;
    private String marcaCarro;
    private String placaCarro;
    private Date dataSaida;
    private UUID idVenda;
}