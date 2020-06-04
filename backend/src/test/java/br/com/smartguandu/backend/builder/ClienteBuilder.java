package br.com.smartguandu.backend.builder;

import br.com.smartguandu.backend.dominio.Cliente;
import br.com.smartguandu.backend.servico.ClienteServico;
import br.com.smartguandu.backend.servico.dto.ClienteExibirDTO;
import br.com.smartguandu.backend.servico.mapper.ClienteCadastrarMapper;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ClienteBuilder {

    private final ClienteServico clienteServico;
    private final ClienteCadastrarMapper clienteCadastrarMapper;

    private Long id = 0L;

    public Cliente build(boolean gerarId) {
        Cliente cliente = new Cliente();

        if (gerarId) cliente.setId(id);
        cliente.setEmail("flavio" + id.toString() + "@gmail.com");
        cliente.setNome("Flávio " + id.toString());
        cliente.setSenha("123456" + id.toString());

        id += 1;
        return cliente;
    }

    public Cliente build() {
        return build(false);
    }

    public List<Cliente> buildMany(int qtde, List<Cliente> clientes) {
        if (qtde < 1) return clientes;

        clientes.add(build());
        return buildMany(qtde - 1, clientes);
    }

    public List<Cliente> buildMany(int qtde) {
        return buildMany(qtde, new ArrayList<>());
    }

    public ClienteExibirDTO persistir(Cliente cliente) {
        return clienteServico.save(clienteCadastrarMapper.toDto(cliente));
    }

    public void persistirMuitos(List<Cliente> clientes) {
        clientes.forEach(cliente -> clienteServico.save(clienteCadastrarMapper.toDto(cliente)));
    }

    public void apagarTodos() {
        if (id > 0) {
            clienteServico.delete(id);
            id -= 1;
            apagarTodos();
        }
    }

    public Long getId() {
        return id;
    }

}
