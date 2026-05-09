package com.byd.project.white.controller;
import com.byd.project.white.dto.DtoCliente;
import com.byd.project.white.service.ClienteService;
import lombok.RequiredArgsConstructor;
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
    public DtoCliente criar(@RequestBody DtoCliente dto) {
        return clienteService.criar(dto);
    }

    @GetMapping
    public List<DtoCliente> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{id}")
    public DtoCliente buscarPorId(@PathVariable UUID id) {
        return clienteService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public DtoCliente atualizar(@PathVariable UUID id, @RequestBody DtoCliente dto) {
        return clienteService.atualizar(id, dto);
    }

    @DeleteMapping("/DeletarConta/{id}")
    public void deletar(@PathVariable UUID id) {
        clienteService.deletar(id);
    }

}
