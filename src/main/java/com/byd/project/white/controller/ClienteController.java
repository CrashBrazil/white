package com.byd.project.white.controller;

import com.byd.project.white.model.Cliente;
import com.byd.project.white.requisicao.DtoClienteRegistrarRequisicao;
import com.byd.project.white.resposta.DtoClienteRegistrarResposta;
import com.byd.project.white.service.ClienteService;
import com.byd.project.white.mapstruct.MapStruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("White")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping("/teste")
    public String authorized(@RequestParam("code") String code) {
        return "Código recebido: " + code;
    }
}
