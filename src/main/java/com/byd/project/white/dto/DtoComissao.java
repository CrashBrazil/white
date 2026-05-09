package com.byd.project.white.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class DtoComissao {

    private UUID idComissao;
    private BigDecimal taxa;
    private String status;
    private BigDecimal valorComissaoFinal;
    private UUID idVendedor;
    private UUID idVenda;
}
