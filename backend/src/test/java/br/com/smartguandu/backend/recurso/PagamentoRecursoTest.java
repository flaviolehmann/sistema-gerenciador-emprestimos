package br.com.smartguandu.backend.recurso;

import br.com.smartguandu.backend.BackendApplication;
import br.com.smartguandu.backend.builder.AgiotaBuilder;
import br.com.smartguandu.backend.builder.ClienteBuilder;
import br.com.smartguandu.backend.builder.PagamentoBuilder;
import br.com.smartguandu.backend.dominio.Pagamento;
import br.com.smartguandu.backend.servico.AgiotaServico;
import br.com.smartguandu.backend.servico.ClienteServico;
import br.com.smartguandu.backend.servico.EmprestimoServico;
import br.com.smartguandu.backend.servico.PagamentoServico;
import br.com.smartguandu.backend.servico.dto.PagamentoCadastrarDTO;
import br.com.smartguandu.backend.servico.mapper.AgiotaCadastrarMapper;
import br.com.smartguandu.backend.servico.mapper.AgiotaExibirMapper;
import br.com.smartguandu.backend.servico.mapper.ClienteCadastrarMapper;
import br.com.smartguandu.backend.servico.mapper.ClienteExibirMapper;
import br.com.smartguandu.backend.servico.mapper.EmprestimoCadastrarMapper;
import br.com.smartguandu.backend.servico.mapper.EmprestimoEditarMapper;
import br.com.smartguandu.backend.servico.mapper.EmprestimoExibirMapper;
import br.com.smartguandu.backend.servico.mapper.EmprestimoListarMapper;
import br.com.smartguandu.backend.servico.mapper.PagamentoCadastrarMapper;
import br.com.smartguandu.backend.servico.mapper.PagamentoExibirMapper;
import br.com.smartguandu.backend.servico.mapper.PagamentoListarMapper;
import br.com.smartguandu.backend.util.TestUtil;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class)
@Transactional
@RequiredArgsConstructor
public class PagamentoRecursoTest {

    private static final String API_PAGAMENTO = "/api/pagamentos/";

    private MockMvc mockMvc;

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

    @Autowired
    private PagamentoCadastrarMapper pagamentoCadastrarMapper;

    @Autowired
    private PagamentoListarMapper pagamentoListarMapper;

    @Autowired
    private PagamentoExibirMapper pagamentoExibirMapper;

    @Autowired
    private PagamentoServico pagamentoServico;

    private ClienteBuilder clienteBuilder;
    private AgiotaBuilder agiotaBuilder;
    private PagamentoBuilder pagamentoBuilder;

    @Before
    public void inicilizarAmbiente() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        clienteBuilder = new ClienteBuilder(clienteServico, clienteCadastrarMapper);
        agiotaBuilder = new AgiotaBuilder(agiotaServico, agiotaCadastrarMapper);
        pagamentoBuilder = new PagamentoBuilder(
                emprestimoServico, emprestimoCadastrarMapper, clienteBuilder, agiotaBuilder,
                clienteExibirMapper, agiotaExibirMapper, pagamentoServico, pagamentoCadastrarMapper,
                emprestimoExibirMapper);
    }

    @Test
    public void deveCadastrarPagamento() throws Exception {
        PagamentoCadastrarDTO pagamento = pagamentoCadastrarMapper.toDto(pagamentoBuilder.build());

        mockMvc.perform(post(API_PAGAMENTO)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(pagamento)))
                .andExpect(status().isCreated());
    }

    @Test
    public void deveExcluirPagamento() throws Exception {
        List<Pagamento> pagamentos = pagamentoBuilder.buildMany(200);
        pagamentoBuilder.persistirMuitos(pagamentos);
        List<Pagamento> todosOsPagamento = pagamentoListarMapper.toEntity(pagamentoServico.list());

        Long idBuscado = todosOsPagamento.get(todosOsPagamento.size() - 1).getId();
        mockMvc.perform(delete(API_PAGAMENTO + idBuscado))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deveListarPagamentos() throws Exception {
        pagamentoBuilder.persistirMuitos(pagamentoBuilder.buildMany(10));

        mockMvc.perform(get(API_PAGAMENTO))
                .andExpect(status().isOk());
    }

    @Test
    public void deveExibirPagamento() throws Exception {
        List<Pagamento> pagamentos = pagamentoBuilder.buildMany(200);
        pagamentoBuilder.persistirMuitos(pagamentos);

        List<Pagamento> pagamentosNoBanco = pagamentoListarMapper.toEntity(pagamentoServico.list());

        Long idBuscado = pagamentosNoBanco.get(pagamentosNoBanco.size() - 1).getId();
        mockMvc.perform(get(API_PAGAMENTO + idBuscado.toString()))
                .andExpect(status().isOk());
    }

}
