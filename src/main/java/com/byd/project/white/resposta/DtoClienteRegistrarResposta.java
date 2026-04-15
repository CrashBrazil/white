package com.byd.project.white.resposta;

import com.byd.project.white.model.enums.TipoSexo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoClienteRegistrarResposta {
    private String emailCliente;
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
}
