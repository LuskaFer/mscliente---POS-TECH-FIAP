package com.fiap.ms_cliente_service.controller;

import com.fiap.ms_cliente_service.domain.entity.DadosPagamento;
import com.fiap.ms_cliente_service.service.DadosPagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class DadosPagamentoController {

    private final DadosPagamentoService service;

    @PostMapping("/cliente/{id}")
    public ResponseEntity<DadosPagamento> cadastrar(@PathVariable Long id, @RequestBody DadosPagamento dto) {
        return ResponseEntity.ok(service.salvar(id, dto));
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<DadosPagamento> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorClienteId(id));
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<DadosPagamento> atualizar(@PathVariable Long id, @RequestBody DadosPagamento dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletarPorClienteId(id);
        return ResponseEntity.noContent().build();
    }


}
