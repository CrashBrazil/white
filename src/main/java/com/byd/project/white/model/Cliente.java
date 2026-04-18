package com.byd.project.white.model;

import com.byd.project.white.model.enums.TipoCargo;
import com.byd.project.white.model.enums.TipoSexo;
import com.byd.project.white.repository.ClienteRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor

@Data
@Entity
//@EqualsAndHashCode(exclude = "vendedorCliente")
public class Cliente implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCliente;

    @NotNull
    @Column(length = 25, unique = true)
    private String emailCliente;

    @NotNull
    private String senhaCliente;

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

    @NotNull
    private TipoCargo cargoCliente;

    private String numeroResidencia;

    @ManyToOne(fetch = FetchType.LAZY)
    //nullable = false
    @JoinColumn(name = "idVendedorCliente",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Vendedor vendedorCliente;

    @OneToMany(mappedBy = "ClienteVenda")
    private List<Venda> VendaCliente;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.cargoCliente == TipoCargo.CLIENTE){
            return List.of(new SimpleGrantedAuthority("ROLE_CLIENTE"));
        }
        else{
            return List.of(new SimpleGrantedAuthority("ROLE_VENDEDOR"));
        }
    }

    @Override
    public String getPassword() {
        return this.senhaCliente;
    }

    @Override
    public String getUsername() {
        return this.emailCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(idCliente, cliente.idCliente) && Objects.equals(emailCliente, cliente.emailCliente) && Objects.equals(senhaCliente, cliente.senhaCliente) && Objects.equals(telefoneCliente, cliente.telefoneCliente) && sexoCliente == cliente.sexoCliente && Objects.equals(dataNascimentoCliente, cliente.dataNascimentoCliente) && Objects.equals(nomeCompletoCliente, cliente.nomeCompletoCliente) && Objects.equals(tipoMoradia, cliente.tipoMoradia) && Objects.equals(complemento, cliente.complemento) && Objects.equals(cidade, cliente.cidade) && Objects.equals(endereco, cliente.endereco) && Objects.equals(cep, cliente.cep) && cargoCliente == cliente.cargoCliente && Objects.equals(numeroResidencia, cliente.numeroResidencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, emailCliente, senhaCliente, telefoneCliente, sexoCliente, dataNascimentoCliente, nomeCompletoCliente, tipoMoradia, complemento, cidade, endereco, cep, cargoCliente, numeroResidencia);
    }
}
