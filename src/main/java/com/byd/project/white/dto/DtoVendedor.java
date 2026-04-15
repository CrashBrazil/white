package com.byd.project.white.dto;

import com.byd.project.white.model.enums.TipoCargo;
import com.byd.project.white.model.enums.TipoSexo;
import com.byd.project.white.model.enums.TipoStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class DtoVendedor {

    @NotNull
    private String nomeCompleto;

    @NotNull
    private String endereco;

    @NotNull
    private Date dataNascimento;

    @NotNull
    private String cpf;

    @NotNull
    private TipoSexo sexo;

    @NotNull
    private String telefone;

    @NotNull
    private String email;

    @NotNull
    private TipoCargo cargo;

    @NotNull
    private BigDecimal salario;

    @NotNull
    private Date dataAdmissao;

    @NotNull
    private TipoStatus status;

    @NotNull
    private String senha;
}