package com.byd.project.white.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoStatusVeiculo {
    DISPONIVEL("Disponivel"),
    RESERVADO("Reservado"),
    VENDIDO("Vendido"),
    MANUTENCAO("Manutenção");

    private final String valor;
}
