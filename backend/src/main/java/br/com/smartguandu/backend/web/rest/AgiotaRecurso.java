package br.com.smartguandu.backend.web.rest;

import br.com.smartguandu.backend.servico.AgiotaServico;
import br.com.smartguandu.backend.servico.dto.AgiotaCadastrarDTO;
import br.com.smartguandu.backend.servico.dto.AgiotaEditarDTO;
import br.com.smartguandu.backend.servico.dto.AgiotaExibirDTO;
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
@RequestMapping("api/agiotas")
@RequiredArgsConstructor
public class AgiotaRecurso {

    private final AgiotaServico agiotaServico;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody AgiotaCadastrarDTO agiota) {
        AgiotaExibirDTO agiotaSalvo = agiotaServico.save(agiota);
        return ResponseEntity.created(URI.create("/agiotas/" + agiotaSalvo.getId()))
                .body(agiotaSalvo);
    }

    @GetMapping
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok(agiotaServico.list());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> show(@PathVariable Long id) {
        return ResponseEntity.ok(agiotaServico.show(id));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody AgiotaEditarDTO agiota) {
        return ResponseEntity.ok(agiotaServico.update(agiota));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        agiotaServico.delete(id);
        return ResponseEntity.noContent().build();
    }
}
