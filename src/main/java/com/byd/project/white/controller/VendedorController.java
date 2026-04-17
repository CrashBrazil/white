package com.byd.project.white.controller;

import com.byd.project.white.dto.DtoVendedor;
import com.byd.project.white.service.VendedorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService service;

    @PostMapping
    public DtoVendedor criar(@RequestBody DtoVendedor dto) {
        return service.criar(dto);
    }

    @GetMapping
    public List<DtoVendedor> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public DtoVendedor buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public DtoVendedor atualizar(@PathVariable UUID id, @RequestBody DtoVendedor dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}