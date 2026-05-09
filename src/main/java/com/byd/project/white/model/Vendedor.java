package com.byd.project.white.model;

import com.byd.project.white.model.enums.TipoCargo;
import com.byd.project.white.model.enums.TipoSexo;
import com.byd.project.white.model.enums.TipoStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vendedor implements UserDetails {

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

    @JsonIgnore
    @OneToMany(mappedBy = "vendedorCliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Cliente> clientes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "VendedorVenda",
            joinColumns = @JoinColumn(name = "idvendedorvenda"),
            inverseJoinColumns = @JoinColumn(name = "idvendavendedor")
    )
    private List<Venda> vendas;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.cargo == TipoCargo.CLIENTE){
            return List.of(new SimpleGrantedAuthority("ROLE_CLIENTE"));
        }
        else if(this.cargo == TipoCargo.VENDEDOR){
            return List.of(new SimpleGrantedAuthority("ROLE_VENDEDOR"));
        }
        else {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vendedor vendedor = (Vendedor) o;
        return Objects.equals(idFuncionario, vendedor.idFuncionario);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idFuncionario);
    }

    @Override
    public @Nullable String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
