package br.com.smartguandu.backend.recurso;

import br.com.smartguandu.backend.BackendApplication;
import br.com.smartguandu.backend.builder.AgiotaBuilder;
import br.com.smartguandu.backend.builder.ClienteBuilder;
import br.com.smartguandu.backend.builder.EmprestimoBuilder;
import br.com.smartguandu.backend.dominio.Agiota;
import br.com.smartguandu.backend.dominio.Cliente;
import br.com.smartguandu.backend.dominio.Emprestimo;
import br.com.smartguandu.backend.servico.AgiotaServico;
import br.com.smartguandu.backend.servico.ClienteServico;
import br.com.smartguandu.backend.servico.EmprestimoServico;
import br.com.smartguandu.backend.servico.dto.EmprestimoCadastrarDTO;
import br.com.smartguandu.backend.servico.mapper.AgiotaCadastrarMapper;
import br.com.smartguandu.backend.servico.mapper.AgiotaExibirMapper;
import br.com.smartguandu.backend.servico.mapper.ClienteCadastrarMapper;
import br.com.smartguandu.backend.servico.mapper.ClienteExibirMapper;
import br.com.smartguandu.backend.servico.mapper.EmprestimoCadastrarMapper;
import br.com.smartguandu.backend.servico.mapper.EmprestimoEditarMapper;
import br.com.smartguandu.backend.servico.mapper.EmprestimoExibirMapper;
import br.com.smartguandu.backend.servico.mapper.EmprestimoListarMapper;
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
public class EmprestimoRecursoTest {

    private static final String API_EMPRESTIMO = "/api/emprestimos/";

    private MockMvc mockMvc;
    private EmprestimoBuilder emprestimoBuilder;
    private ClienteBuilder clienteBuilder;
    private AgiotaBuilder agiotaBuilder;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private EmprestimoServico emprestimoServico;

    @Autowired
    private EmprestimoCadastrarMapper emprestimoCadastrarMapper;

    @Autowired
    private EmprestimoExibirMapper emprestimoExibirMapper;

    @Autowired
    private EmprestimoListarMapper emprestimoListarMapper;

    @Autowired
    private EmprestimoEditarMapper emprestimoEditarMapper;

    @Autowired
    private ClienteServico clienteServico;

    @Autowired
    private ClienteCadastrarMapper clienteCadastrarMapper;

    @Autowired
    private AgiotaServico agiotaServico;

    @Autowired
    private AgiotaCadastrarMapper agiotaCadastrarMapper;

    @Autowired
    private ClienteExibirMapper clienteExibirMapper;

    @Autowired
    private AgiotaExibirMapper agiotaExibirMapper;

    @Before
    public void inicilizarAmbiente() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        clienteBuilder = new ClienteBuilder(clienteServico, clienteCadastrarMapper);
        agiotaBuilder = new AgiotaBuilder(agiotaServico, agiotaCadastrarMapper);
        emprestimoBuilder = new EmprestimoBuilder(
                emprestimoServico, emprestimoCadastrarMapper, clienteBuilder,
                agiotaBuilder, clienteExibirMapper, agiotaExibirMapper);
    }

    @Test
    public void deveCadastrarEmprestimo() throws Exception {
        EmprestimoCadastrarDTO emprestimo = emprestimoCadastrarMapper.toDto(emprestimoBuilder.build());

        mockMvc.perform(post(API_EMPRESTIMO)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(emprestimo)))
                .andExpect(status().isCreated());
    }

    @Test
    public void deveExcluirEmprestimo() throws Exception {
        List<Emprestimo> emprestimos = emprestimoBuilder.buildMany(200);
        emprestimoBuilder.persistirMuitos(emprestimos);
        List<Emprestimo> todosOsEmprestimos = emprestimoListarMapper.toEntity(emprestimoServico.list());

        Long idBuscado = todosOsEmprestimos.get(todosOsEmprestimos.size() - 1).getId();
        mockMvc.perform(delete(API_EMPRESTIMO + idBuscado))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deveAtualizarCobrador() throws Exception {
        emprestimoBuilder.persistirMuitos(emprestimoBuilder.buildMany(200));

        ObjectMapper objectMapper = new ObjectMapper();

        List<Emprestimo> emprestimoesNoBanco = emprestimoListarMapper.toEntity(emprestimoServico.list());
        Emprestimo ultimoEmprestimoInserido = emprestimoesNoBanco.get(emprestimoesNoBanco.size() - 1);

        ultimoEmprestimoInserido.setValor(1300L);

        Cliente cliente = clienteBuilder.build();
        clienteBuilder.persistir(cliente);
        ultimoEmprestimoInserido.setCliente(cliente);

        Agiota agiota = agiotaBuilder.build();
        agiotaBuilder.persistir(agiota);
        ultimoEmprestimoInserido.setAgiota(agiota);

        mockMvc.perform(put(API_EMPRESTIMO)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(emprestimoEditarMapper.toDto(ultimoEmprestimoInserido))))
                .andExpect(status().isOk());
    }

    @Test
    public void deveListarCobradores() throws Exception {
        emprestimoBuilder.persistirMuitos(emprestimoBuilder.buildMany(10));

        mockMvc.perform(get(API_EMPRESTIMO))
                .andExpect(status().isOk());
    }

    @Test
    public void deveExibirCobrador() throws Exception {
        List<Emprestimo> emprestimos = emprestimoBuilder.buildMany(200);
        emprestimoBuilder.persistirMuitos(emprestimos);

        List<Emprestimo> emprestimosNoBanco = emprestimoListarMapper.toEntity(emprestimoServico.list());

        Long idBuscado = emprestimosNoBanco.get(emprestimosNoBanco.size() - 1).getId();
        mockMvc.perform(get(API_EMPRESTIMO + idBuscado.toString()))
                .andExpect(status().isOk());

    }

}
