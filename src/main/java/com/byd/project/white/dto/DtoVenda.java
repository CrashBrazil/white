package com.byd.project.white.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class DtoVenda {
    private UUID idVenda;
    private LocalDateTime dataVenda;
    private String veiculoVendido;
    private UUID idVendedor;
    private BigDecimal valorVenda;
    private BigDecimal descontoVenda;
    private BigDecimal valorFinalVenda;
    private String statusVenda;
    private String tipoPagamento;
    private UUID idCliente;

}
