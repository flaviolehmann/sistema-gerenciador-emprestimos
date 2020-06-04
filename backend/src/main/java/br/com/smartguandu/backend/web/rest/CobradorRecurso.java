package br.com.smartguandu.backend.web.rest;

import br.com.smartguandu.backend.servico.CobradorServico;
import br.com.smartguandu.backend.servico.dto.CobradorCadastrarDTO;
import br.com.smartguandu.backend.servico.dto.CobradorEditarDTO;
import br.com.smartguandu.backend.servico.dto.CobradorExibirDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/cobradores")
@RequiredArgsConstructor
public class CobradorRecurso {

    private final CobradorServico cobradorServico;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CobradorCadastrarDTO cobrador) {
        CobradorExibirDTO cobradorSalvo = cobradorServico.save(cobrador);
        return ResponseEntity.created(URI.create("/cobradores/" + cobradorSalvo.getId()))
                .body(cobradorSalvo);
    }

    @GetMapping
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok(cobradorServico.list());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> show(@PathVariable Long id) {
        return ResponseEntity.ok(cobradorServico.show(id));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody CobradorEditarDTO cobrador) {
        return ResponseEntity.ok(cobradorServico.update(cobrador));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        cobradorServico.delete(id);
        return ResponseEntity.noContent().build();
    }

}
