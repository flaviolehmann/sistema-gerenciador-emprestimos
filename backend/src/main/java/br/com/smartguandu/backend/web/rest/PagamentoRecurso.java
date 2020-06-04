package br.com.smartguandu.backend.web.rest;

import br.com.smartguandu.backend.servico.PagamentoServico;
import br.com.smartguandu.backend.servico.dto.PagamentoCadastrarDTO;
import br.com.smartguandu.backend.servico.dto.PagamentoExibirDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("api/pagamentos")
@RequiredArgsConstructor
public class PagamentoRecurso {

    private final PagamentoServico pagamentoServico;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody PagamentoCadastrarDTO pagamento) {
        PagamentoExibirDTO pagamentoSalvo = pagamentoServico.save(pagamento);
        return ResponseEntity.created(URI.create("/pagamentos/" + pagamentoSalvo.getId()))
                .body(pagamentoSalvo);
    }

    @GetMapping
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok(pagamentoServico.list());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> show(@PathVariable Long id) {
        return ResponseEntity.ok(pagamentoServico.show(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        pagamentoServico.delete(id);
        return ResponseEntity.noContent().build();
    }

}
