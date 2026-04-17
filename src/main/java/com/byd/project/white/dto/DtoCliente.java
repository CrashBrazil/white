package com.byd.project.white.dto;

import lombok.Data;

import java.util.UUID;
import java.sql.Date;

@Data
public class DtoCliente {

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