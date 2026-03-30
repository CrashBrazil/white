package com.byd.project.white.model;

import com.byd.project.white.model.enums.TipoCargo;
import com.byd.project.white.model.enums.TipoSexo;
import com.byd.project.white.model.enums.TipoStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idFuncionario;

    @Column(length = 25)
    @NotNull
    private String nomeCompleto;

    @Column(length = 25)
    @NotNull
    private String endereco;

    @NotNull
    private Date dataNascimento;

    @NotNull
    @Column(unique = true)
    private String cpf;

    @NotNull
    private TipoSexo sexo;

    @NotNull
    private String telefone;

    @NotNull
    @Column(unique = true)
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

    private Date dataDemissao;







}
