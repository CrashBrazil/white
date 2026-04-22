package com.byd.project.white.controller;

import com.byd.project.white.dto.DtoComissao;
import com.byd.project.white.service.ComissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comissao")
public class ComissaoController {

    @Autowired
    private ComissaoService service;

    @PostMapping
    public DtoComissao criar(@RequestBody DtoComissao dto) {
        return service.criar(dto);
    }

    @GetMapping
    public List<DtoComissao> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public DtoComissao buscarPorID(@PathVariable UUID id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public DtoComissao atualizar(@PathVariable UUID id, @RequestBody DtoComissao dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}
