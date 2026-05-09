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

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("white")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping("/teste")
    public String authorized(@RequestParam(value = "code", required = false)  String code) {
        return "Código recebido: " + code;
    }

    @PostMapping("/registrarcliente")
    public DtoClienteRegistrarRequisicao criar(@RequestBody DtoClienteRegistrarRequisicao dto) {
        return clienteService.criar(dto);
    }

    @GetMapping("/listarcliente")
    public List<DtoClienteRegistrarRequisicao> listar() {
        return clienteService.listar();
    }

    @GetMapping("/buscarporidcliente/{id}")
    public DtoClienteRegistrarRequisicao buscarPorId(@PathVariable UUID id) {
        return clienteService.buscarPorId(id);
    }

    @PutMapping("/atualizarcliente/{id}")
    public DtoClienteRegistrarRequisicao atualizar(@PathVariable("id") UUID id, @RequestBody DtoClienteRegistrarRequisicao dto) {
        return clienteService.atualizar(id, dto);
    }

    @DeleteMapping("/deletarcontacliente/{id}")
    public void deletar(@PathVariable UUID id) {
        clienteService.deletar(id);
    }

}
