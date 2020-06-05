package br.com.smartguandu.backend.recurso;

import br.com.smartguandu.backend.BackendApplication;
import br.com.smartguandu.backend.builder.ClienteBuilder;
import br.com.smartguandu.backend.dominio.Cliente;
import br.com.smartguandu.backend.servico.ClienteServico;
import br.com.smartguandu.backend.servico.dto.ClienteCadastrarDTO;
import br.com.smartguandu.backend.servico.mapper.ClienteCadastrarMapper;
import br.com.smartguandu.backend.servico.mapper.ClienteEditarMapper;
import br.com.smartguandu.backend.servico.mapper.ClienteExibirMapper;
import br.com.smartguandu.backend.servico.mapper.ClienteListarMapper;
import br.com.smartguandu.backend.util.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class)
@Transactional
@RequiredArgsConstructor
public class ClienteRecursoTest {

    private static final String API_CLIENTE = "/api/clientes/";

    private MockMvc mockMvc;
    private ClienteBuilder clienteBuilder;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ClienteServico clienteServico;

    @Autowired
    private ClienteCadastrarMapper clienteCadastrarMapper;

    @Autowired
    private ClienteExibirMapper clienteExibirMapper;

    @Autowired
    private ClienteListarMapper clienteListarMapper;

    @Autowired
    private ClienteEditarMapper clienteEditarMapper;

    @Before
    public void inicilizarAmbiente() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        clienteBuilder = new ClienteBuilder(clienteServico, clienteCadastrarMapper);
        // clienteBuilder.apagarTodos();
    }

    @Test
    public void deveCadastrarCliente() throws Exception {
        ClienteCadastrarDTO cliente =  clienteCadastrarMapper.toDto(clienteBuilder.build());

        mockMvc.perform(post(API_CLIENTE)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(cliente)))
                .andExpect(status().isCreated());
    }

    @Test
    public void deveExcluirCliente() throws Exception {
        List<Cliente> clientes = clienteBuilder.buildMany(200);
        clienteBuilder.persistirMuitos(clientes);
        List<Cliente> todosOsClientes = clienteListarMapper.toEntity(clienteServico.list());

        Long idBuscado = todosOsClientes.get(todosOsClientes.size() - 1).getId();
        mockMvc.perform(delete(API_CLIENTE + idBuscado))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deveAtualizarCliente() throws Exception {
        clienteBuilder.persistirMuitos(clienteBuilder.buildMany(200));

        ObjectMapper objectMapper = new ObjectMapper();

        List<Cliente> clientesNoBanco = clienteListarMapper.toEntity(clienteServico.list());
        Cliente ultimoClienteInserido = clientesNoBanco.get(clientesNoBanco.size() - 1);

        ultimoClienteInserido.setNome("TODYINHO");
        ultimoClienteInserido.setSenha("TESTE");
        ultimoClienteInserido.setEmail("TEST@TESTE.co");

        mockMvc.perform(put(API_CLIENTE)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(clienteEditarMapper.toDto(ultimoClienteInserido))))
                .andExpect(status().isOk());
    }

    @Test
    public void deveListarClientes() throws Exception {
        clienteBuilder.persistirMuitos(clienteBuilder.buildMany(10));

        mockMvc.perform(get(API_CLIENTE))
                .andExpect(status().isOk());
    }

    @Test
    public void deveExibirCliente() throws Exception {
        List<Cliente> clientes = clienteBuilder.buildMany(200);
        clienteBuilder.persistirMuitos(clientes);

        List<Cliente> clientesNoBanco = clienteListarMapper.toEntity(clienteServico.list());

        Long idBuscado = clientesNoBanco.get(clientesNoBanco.size() - 1).getId();
        mockMvc.perform(get(API_CLIENTE + idBuscado.toString()))
                .andExpect(status().isOk());

    }

}
