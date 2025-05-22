package com.fiap.mscliente.controller;

import com.fiap.mscliente.domain.DadosPagamento;
import com.fiap.mscliente.dto.DadosPagamentoDTO;
import com.fiap.mscliente.gateway.database.jpa.mapper.DadosPagamentoMapper;
import com.fiap.mscliente.usecases.DadosPagamentoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class DadosPagamentoController {

    private final DadosPagamentoUseCase useCase;
    private final DadosPagamentoMapper mapper;

    @PostMapping
    public ResponseEntity<DadosPagamentoDTO> salvar(@RequestBody DadosPagamentoDTO dto) {
        DadosPagamento pagamento = mapper.toDomain(dto);
        DadosPagamento salvo = useCase.salvar(pagamento);
        return ResponseEntity.ok(mapper.toDto(salvo));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<DadosPagamentoDTO> buscarPorClienteId(@PathVariable Long clienteId) {
        return useCase.buscarPorClienteId(clienteId)
                .map(p -> ResponseEntity.ok(mapper.toDto(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosPagamentoDTO> atualizar(@PathVariable Long id, @RequestBody DadosPagamentoDTO dto) {
        DadosPagamento atualizado = useCase.atualizar(id, mapper.toDomain(dto));
        return ResponseEntity.ok(mapper.toDto(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        useCase.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
