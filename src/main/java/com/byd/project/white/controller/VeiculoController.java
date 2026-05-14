package com.byd.project.white.controller;

import com.byd.project.white.dto.DtoVeiculo;
import com.byd.project.white.service.VeiculoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/white/veiculo")
public class VeiculoController {


    private final VeiculoService service;

    @PostMapping("/criar")
    public DtoVeiculo criar(@RequestBody DtoVeiculo dto) {
        return service.criar(dto);
    }

    @GetMapping("/listar")
    public List<DtoVeiculo> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/buscarporid/{id}")
    public DtoVeiculo buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/atualizar/{id}")
    public DtoVeiculo atualizar(@PathVariable UUID id, @RequestBody DtoVeiculo dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}