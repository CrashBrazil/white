package com.byd.project.white.controller;


import com.byd.project.white.dto.DtoVendedor;
import com.byd.project.white.service.VendedorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/white")
@AllArgsConstructor
public class VendedorController {


    private final VendedorService service;

    @PostMapping("/registrarvendedor")
    public DtoVendedor criar(@RequestBody DtoVendedor dto) {
        return service.criar(dto);
    }

    @GetMapping("/listartodos")
    public List<DtoVendedor> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/buscarporidvendedor/{id}")
    public DtoVendedor buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/atualizarvendedor/{id}")
    public DtoVendedor atualizar(@PathVariable UUID id, @RequestBody DtoVendedor dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/deletarvendedor/{id}")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}