package com.byd.project.white.controller;

import com.byd.project.white.dto.DtoVenda;
import com.byd.project.white.service.VendaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/white/venda")
@AllArgsConstructor
public class VendaController {

    private final VendaService vendaService;

    @PostMapping("/criar")
    public DtoVenda criar(@RequestBody DtoVenda dto) {
        return vendaService.criar(dto);
    }

    @GetMapping("/listar")
    public List<DtoVenda> listar() {
        return vendaService.listarTodas();
    }

    @GetMapping("/buscarporid/{id}")
    public DtoVenda buscarPorId(@PathVariable UUID id) {
        return vendaService.buscarPorId(id);
    }

    @PutMapping("/atualizar/{id}")
    public DtoVenda atualizar(@PathVariable UUID id, @RequestBody DtoVenda dto) {
        return vendaService.atualizar(id, dto);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable UUID id) {
        vendaService.deletar(id);
    }
}