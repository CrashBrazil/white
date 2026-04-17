package com.byd.project.white.service.impl;

import com.byd.project.white.requisicao.DtoClienteRegistrarRequisicao;
import com.byd.project.white.model.Cliente;

import java.sql.SQLIntegrityConstraintViolationException;

public interface ClienteServiceInterface {
    Cliente registrar(DtoClienteRegistrarRequisicao dtoClienteRegistrarRequisicao) throws SQLIntegrityConstraintViolationException;

}
