package com.byd.project.white.controller;

import com.byd.project.white.dto.DtoComissao;
import com.byd.project.white.service.ComissaoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/white/comissao")
@AllArgsConstructor
public class ComissaoController {


    private final ComissaoService service;

    @PostMapping("criar")
    public DtoComissao criar(@RequestBody DtoComissao dto) {
        return service.criar(dto);
    }

    @GetMapping("listar")
    public List<DtoComissao> listar() {
        return service.listarTodos();
    }

    @GetMapping("buscarporid/{id}")
    public DtoComissao buscarPorID(@PathVariable UUID id) {
        return service.buscarPorId(id);
    }

    @PutMapping("atualizar/{id}")
    public DtoComissao atualizar(@PathVariable UUID id, @RequestBody DtoComissao dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/deletar")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}
