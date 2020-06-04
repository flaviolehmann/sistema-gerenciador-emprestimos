package br.com.smartguandu.backend.recurso;

import br.com.smartguandu.backend.BackendApplication;
import br.com.smartguandu.backend.builder.AgiotaBuilder;
import br.com.smartguandu.backend.dominio.Agiota;
import br.com.smartguandu.backend.servico.AgiotaServico;
import br.com.smartguandu.backend.servico.dto.AgiotaCadastrarDTO;
import br.com.smartguandu.backend.servico.mapper.AgiotaCadastrarMapper;
import br.com.smartguandu.backend.servico.mapper.AgiotaEditarMapper;
import br.com.smartguandu.backend.servico.mapper.AgiotaExibirMapper;
import br.com.smartguandu.backend.servico.mapper.AgiotaListarMapper;
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
public class AgiotaRecursoTest {

    private static final String API_AGIOTA = "/api/agiotas/";

    private MockMvc mockMvc;
    private AgiotaBuilder agiotaBuilder;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AgiotaServico agiotaServico;

    @Autowired
    private AgiotaCadastrarMapper agiotaCadastrarMapper;

    @Autowired
    private AgiotaExibirMapper agiotaExibirMapper;

    @Autowired
    private AgiotaListarMapper agiotaListarMapper;

    @Autowired
    private AgiotaEditarMapper agiotaEditarMapper;

    @Before
    public void inicilizarAmbiente() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        agiotaBuilder = new AgiotaBuilder(agiotaServico, agiotaCadastrarMapper);
    }

    @Test
    public void deveCadastrarAgiota() throws Exception {
        AgiotaCadastrarDTO agiota =  agiotaCadastrarMapper.toDto(agiotaBuilder.build());

        mockMvc.perform(post(API_AGIOTA)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(agiota)))
                .andExpect(status().isCreated());
    }

    @Test
    public void deveExcluirAgiota() throws Exception {
        List<Agiota> agiotas = agiotaBuilder.buildMany(200);
        agiotaBuilder.persistirMuitos(agiotas);
        List<Agiota> todosOsAgiotas = agiotaListarMapper.toEntity(agiotaServico.list());

        Long idBuscado = todosOsAgiotas.get(todosOsAgiotas.size() - 1).getId();
        mockMvc.perform(delete(API_AGIOTA + idBuscado))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deveAtualizarAgiota() throws Exception {
        agiotaBuilder.persistirMuitos(agiotaBuilder.buildMany(200));

        ObjectMapper objectMapper = new ObjectMapper();

        List<Agiota> agiotasNoBanco = agiotaListarMapper.toEntity(agiotaServico.list());
        Agiota ultimoAgiotaInserido = agiotasNoBanco.get(agiotasNoBanco.size() - 1);

        ultimoAgiotaInserido.setNome("TODYINHO");
        ultimoAgiotaInserido.setSenha("TESTE");
        ultimoAgiotaInserido.setEmail("TEST@TESTE.co");

        mockMvc.perform(put(API_AGIOTA)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(agiotaEditarMapper.toDto(ultimoAgiotaInserido))))
                .andExpect(status().isOk());
    }

    @Test
    public void deveListarAgiotas() throws Exception {
        agiotaBuilder.persistirMuitos(agiotaBuilder.buildMany(10));

        mockMvc.perform(get(API_AGIOTA))
                .andExpect(status().isOk());
    }

    @Test
    public void deveExibirAgiota() throws Exception {
        List<Agiota> agiotas = agiotaBuilder.buildMany(200);
        agiotaBuilder.persistirMuitos(agiotas);

        List<Agiota> agiotasNoBanco = agiotaListarMapper.toEntity(agiotaServico.list());

        Long idBuscado = agiotasNoBanco.get(agiotasNoBanco.size() - 1).getId();
        mockMvc.perform(get(API_AGIOTA + idBuscado.toString()))
                .andExpect(status().isOk());

    }

}
