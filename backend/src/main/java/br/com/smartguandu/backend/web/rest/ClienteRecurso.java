package br.com.smartguandu.backend.web.rest;

import br.com.smartguandu.backend.servico.ClienteServico;
import br.com.smartguandu.backend.servico.dto.ClienteCadastrarDTO;
import br.com.smartguandu.backend.servico.dto.ClienteEditarDTO;
import br.com.smartguandu.backend.servico.dto.ClienteExibirDTO;
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
@RequestMapping("api/clientes")
@RequiredArgsConstructor
public class ClienteRecurso {

    private final ClienteServico clienteServico;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody ClienteCadastrarDTO cliente) {
        ClienteExibirDTO clienteSalvo = clienteServico.save(cliente);
//        return null;
        return ResponseEntity.created(URI.create("/clientes/" + clienteSalvo.getId()))
                .body(clienteSalvo);
    }

    @GetMapping
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok(clienteServico.list());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> show(@PathVariable Long id) {
        return ResponseEntity.ok(clienteServico.show(id));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody ClienteEditarDTO cliente) {
        return ResponseEntity.ok(clienteServico.update(cliente));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        clienteServico.delete(id);
        return ResponseEntity.noContent().build();
    }
}
