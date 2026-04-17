package com.byd.project.white.controller;
import com.byd.project.white.requisicao.DtoVendedor;
import com.byd.project.white.service.VendedorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vendedores")
@AllArgsConstructor
public class VendedorController {


    private final VendedorService service;

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