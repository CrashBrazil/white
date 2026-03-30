package com.byd.project.white.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoCargo {

    VENDEDOR("Vendedor"),
    CLIENTE("Cliente");

    private final String VALOR;


}
