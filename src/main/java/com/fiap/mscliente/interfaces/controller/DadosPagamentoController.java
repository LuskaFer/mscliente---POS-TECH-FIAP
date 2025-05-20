package com.fiap.mscliente.interfaces.controller;

import com.fiap.mscliente.domain.entity.DadosPagamento;
import com.fiap.mscliente.application.service.DadosPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
public class DadosPagamentoController {

    private final DadosPagamentoService service;

    @Autowired
    public DadosPagamentoController(DadosPagamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DadosPagamento> salvar(@RequestBody DadosPagamento dadosPagamento) {
        return service.salvar(dadosPagamento);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<DadosPagamento> buscarPorClienteId(@PathVariable Long clienteId) {
        return service.buscarPorClienteId(clienteId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosPagamento> atualizar(@PathVariable Long id, @RequestBody DadosPagamento dados) {
        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        return service.excluir(id); // ✅ correto
    }

}
