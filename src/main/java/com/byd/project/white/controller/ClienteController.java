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
@RequestMapping("White")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping("/teste")
    public String authorized(@RequestParam("code") String code) {
        return "Código recebido: " + code;
    }

    @PostMapping("/Registrar")
    public DtoClienteRegistrarRequisicao criar(@RequestBody DtoClienteRegistrarRequisicao dto) {
        return clienteService.criar(dto);
    }

    @GetMapping
    public List<DtoClienteRegistrarRequisicao> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{id}")
    public DtoClienteRegistrarRequisicao buscarPorId(@PathVariable UUID id) {
        return clienteService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public DtoClienteRegistrarRequisicao atualizar(@PathVariable UUID id, @RequestBody DtoClienteRegistrarRequisicao dto) {
        return clienteService.atualizar(id, dto);
    }

    @DeleteMapping("/DeletarConta/{id}")
    public void deletar(@PathVariable UUID id) {
        clienteService.deletar(id);
    }

}
