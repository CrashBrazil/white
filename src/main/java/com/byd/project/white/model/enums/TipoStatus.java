package com.byd.project.white.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoStatus {
    CANCELADO("Cancelado"),
    PENDENTE("Pendente"),
    PROCESSANDO("Processando"),
    EFETIVADO("Efetivado");

    private final String valor;



}
