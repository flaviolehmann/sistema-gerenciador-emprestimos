package br.com.smartguandu.backend.web.rest;

import br.com.smartguandu.backend.servico.EmprestimoServico;
import br.com.smartguandu.backend.servico.dto.EmprestimoCadastrarDTO;
import br.com.smartguandu.backend.servico.dto.EmprestimoEditarDTO;
import br.com.smartguandu.backend.servico.dto.EmprestimoExibirDTO;
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
@RequestMapping("api/emprestimos")
@RequiredArgsConstructor
public class EmprestimoRecurso {

    private final EmprestimoServico emprestimoServico;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody EmprestimoCadastrarDTO emprestimo) {
        EmprestimoExibirDTO emprestimoSalvo = emprestimoServico.save(emprestimo);
        return ResponseEntity.created(URI.create("/cobradores/" + emprestimoSalvo.getId()))
                .body(emprestimoSalvo);
    }

    @GetMapping
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok(emprestimoServico.list());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> show(@PathVariable Long id) {
        return ResponseEntity.ok(emprestimoServico.show(id));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody EmprestimoEditarDTO emprestimo) {
        return ResponseEntity.ok(emprestimoServico.update(emprestimo));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        emprestimoServico.delete(id);
        return ResponseEntity.noContent().build();
    }
}
