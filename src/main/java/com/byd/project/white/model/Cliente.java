package com.byd.project.white.model;

import com.byd.project.white.model.enums.TipoSexo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Length;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCliente;

    @NotNull
    @Column(length = 25, unique = true)
    private String emailCliente;

    @NotNull
    @Column(length = 11)
    private String telefoneCliente;

    @NotNull
    private TipoSexo sexoCliente;

    @NotNull
    private Date dataNascimentoCliente;

    @NotNull
    @Column(length = 25)
    private String nomeCompletoCliente;

    @NotNull
    @Column(length = 25)
    private String tipoMoradia;

    @Column(length = 30)
    private String complemento;

    @NotNull
    @Column(length = 25)
    private String cidade;

    @NotNull
    @Column(length = 25)
    private String endereco;

    @NotNull
    private String cep;

    private String numeroResidencia;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVendedorCliente", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Vendedor vendedorCliente;

    @JsonIgnore
    @OneToMany(mappedBy = "clienteVenda")
    private List<Venda> VendaCliente;





}
