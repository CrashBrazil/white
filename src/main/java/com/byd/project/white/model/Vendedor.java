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
import java.util.List;
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

    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comissao> comissao;

    @OneToMany(mappedBy = "vendedorCliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Cliente> clientes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "VendedorVenda",
            joinColumns = @JoinColumn(name = "idvendedorvenda"),
            inverseJoinColumns = @JoinColumn(name = "idvendavendedor")
    )
    private List<Venda> vendas;






}
