package com.byd.project.white.controller;


import com.byd.project.white.dto.DtoVendedor;
import com.byd.project.white.service.VendedorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/white/vendedor")
@AllArgsConstructor
public class VendedorController {


    private final VendedorService service;

    @PostMapping("/registrar")
    public DtoVendedor criar(@RequestBody DtoVendedor dto) {
        return service.criar(dto);
    }

    @GetMapping("/listar")
    public List<DtoVendedor> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/buscarporid/{id}")
    public DtoVendedor buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/atualizar/{id}")
    public DtoVendedor atualizar(@PathVariable UUID id, @RequestBody DtoVendedor dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}