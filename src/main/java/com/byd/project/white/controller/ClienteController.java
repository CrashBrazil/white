package com.byd.project.white.controller;

import com.byd.project.white.model.Cliente;
import com.byd.project.white.requisicao.DtoClienteRegistrarRequisicao;
import com.byd.project.white.resposta.DtoClienteRegistrarResposta;
import com.byd.project.white.service.ClienteService;
import com.byd.project.white.mapstruct.MapStruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("White")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping(path = "/Registro")
    public ResponseEntity<DtoClienteRegistrarResposta> clienteResponseEntity(@RequestBody DtoClienteRegistrarRequisicao dtoClienteRegistrarRequisicao){
        try{
            Cliente cliente = clienteService.registrar(dtoClienteRegistrarRequisicao);
            if (cliente != null) {
                return new ResponseEntity<>(MapStruct.INSTANCE.converterCliente(dtoClienteRegistrarRequisicao), HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/teste")
    public String authorized(@RequestParam("code") String code) {
        return "Código recebido: " + code;
    }
}
