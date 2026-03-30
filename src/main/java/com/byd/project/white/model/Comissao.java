package com.byd.project.white.model;

import com.byd.project.white.model.enums.TipoStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    // Já dividido
    @NotNull
    private BigDecimal taxa;

    @NotNull
    private TipoStatus status;

    private BigDecimal valorComissaoFinal;


}
