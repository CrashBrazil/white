package com.byd.project.white.controller;

import com.byd.project.white.dto.DtoCliente;
import com.byd.project.white.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public DtoCliente criar(@RequestBody DtoCliente dto) {
        return service.criar(dto);
    }

    @GetMapping
    public List<DtoCliente> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public DtoCliente buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public DtoCliente atualizar(@PathVariable UUID id, @RequestBody DtoCliente dto) {  // ← Recebe DtoCliente
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}