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
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoClienteRegistrarRequisicao {
    private String nomeCompletoCliente;
    private String emailCliente;
    private String telefoneCliente;
    private String sexoCliente;
    private Date dataNascimentoCliente;
    private String tipoMoradia;
    private String cidade;
    private String endereco;
    private String cep;
    private UUID idVendedor;
}
