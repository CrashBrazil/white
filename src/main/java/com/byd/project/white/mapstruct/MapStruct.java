package com.byd.project.white.mapstruct;

import com.byd.project.white.model.Cliente;
import com.byd.project.white.requisicao.DtoClienteRegistrarRequisicao;
import com.byd.project.white.resposta.DtoClienteRegistrarResposta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "Spring")
public abstract class MapStruct {
    public static final MapStruct INSTANCE = Mappers.getMapper(MapStruct.class);

    public abstract DtoClienteRegistrarResposta converterCliente(DtoClienteRegistrarRequisicao dtoClienteRegistrarRequisicao);
    public abstract Cliente converterparaCliente(DtoClienteRegistrarRequisicao dtoClienteRegistrarRequisicao);

}


