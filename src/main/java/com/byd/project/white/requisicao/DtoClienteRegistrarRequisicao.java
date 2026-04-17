package com.byd.project.white.requisicao;

import com.byd.project.white.model.Venda;
import com.byd.project.white.model.Vendedor;
import com.byd.project.white.model.enums.TipoSexo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoClienteRegistrarRequisicao {
    private String emailCliente;
    private String senhaCliente;
    private String telefoneCliente;
    private TipoSexo sexoCliente;
    private Date dataNascimentoCliente;
    private String nomeCompletoCliente;
    private String tipoMoradia;
    private String complemento;
    private String cidade;
    private String endereco;
    private String cep;
    private String numeroResidencia;
    @ManyToOne(fetch = FetchType.LAZY)
    //nullable = false
    @JoinColumn(name = "idVendedorCliente")
    private Vendedor vendedorCliente;

    @OneToMany(mappedBy = "ClienteVenda")
    private List<Venda> VendaCliente;

}
