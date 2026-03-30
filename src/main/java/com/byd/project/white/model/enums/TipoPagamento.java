package com.byd.project.white.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoPagamento {
    CREDITO("Credito"),
    DEBITO("Debito"),
    PIX("Pix"),
    CONSORCIO("Consorcio"),
    DINHEIRO("Dinheiro");

    private final String VALOR;

}
