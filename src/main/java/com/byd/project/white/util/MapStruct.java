package com.byd.project.white.util;

import com.byd.project.white.dto.DtoCliente;
import com.byd.project.white.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "Spring")
public abstract class MapStruct {
    public static final MapStruct INSTANCE = Mappers.getMapper(MapStruct.class);

    public abstract Cliente converterCliente(DtoCliente dtoCliente);
}
