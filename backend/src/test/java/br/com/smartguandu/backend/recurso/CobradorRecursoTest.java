package br.com.smartguandu.backend.recurso;

import br.com.smartguandu.backend.BackendApplication;
import br.com.smartguandu.backend.builder.CobradorBuilder;
import br.com.smartguandu.backend.dominio.Cobrador;
import br.com.smartguandu.backend.servico.CobradorServico;
import br.com.smartguandu.backend.servico.dto.CobradorCadastrarDTO;
import br.com.smartguandu.backend.servico.mapper.CobradorCadastrarMapper;
import br.com.smartguandu.backend.servico.mapper.CobradorEditarMapper;
import br.com.smartguandu.backend.servico.mapper.CobradorExibirMapper;
import br.com.smartguandu.backend.servico.mapper.CobradorListarMapper;
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
public class CobradorRecursoTest {

    private static final String API_COBRADOR = "/api/cobradores/";

    private MockMvc mockMvc;
    private CobradorBuilder cobradorBuilder;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CobradorServico cobradorServico;

    @Autowired
    private CobradorCadastrarMapper cobradorCadastrarMapper;

    @Autowired
    private CobradorExibirMapper cobradorExibirMapper;

    @Autowired
    private CobradorListarMapper cobradorListarMapper;

    @Autowired
    private CobradorEditarMapper cobradorEditarMapper;

    @Before
    public void inicilizarAmbiente() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        cobradorBuilder = new CobradorBuilder(cobradorServico, cobradorCadastrarMapper);
    }

    @Test
    public void deveCadastrarCobrador() throws Exception {
        CobradorCadastrarDTO cobrador =  cobradorCadastrarMapper.toDto(cobradorBuilder.build());

        mockMvc.perform(post(API_COBRADOR)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(cobrador)))
                .andExpect(status().isCreated());
    }

    @Test
    public void deveExcluirCobrador() throws Exception {
        List<Cobrador> cobradores = cobradorBuilder.buildMany(200);
        cobradorBuilder.persistirMuitos(cobradores);
        List<Cobrador> todosOsCobradores = cobradorListarMapper.toEntity(cobradorServico.list());

        Long idBuscado = todosOsCobradores.get(todosOsCobradores.size() - 1).getId();
        mockMvc.perform(delete(API_COBRADOR + idBuscado))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deveAtualizarCobrador() throws Exception {
        cobradorBuilder.persistirMuitos(cobradorBuilder.buildMany(200));

        ObjectMapper objectMapper = new ObjectMapper();

        List<Cobrador> cobradoresNoBanco = cobradorListarMapper.toEntity(cobradorServico.list());
        Cobrador ultimoCobradorInserido = cobradoresNoBanco.get(cobradoresNoBanco.size() - 1);

        ultimoCobradorInserido.setNome("TODYINHO");
        ultimoCobradorInserido.setSenha("TESTE");
        ultimoCobradorInserido.setEmail("TEST@TESTE.co");

        mockMvc.perform(put(API_COBRADOR)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(cobradorEditarMapper.toDto(ultimoCobradorInserido))))
                .andExpect(status().isOk());
    }

    @Test
    public void deveListarCobradores() throws Exception {
        cobradorBuilder.persistirMuitos(cobradorBuilder.buildMany(10));

        mockMvc.perform(get(API_COBRADOR))
                .andExpect(status().isOk());
    }

    @Test
    public void deveExibirCobrador() throws Exception {
        List<Cobrador> cobradores = cobradorBuilder.buildMany(200);
        cobradorBuilder.persistirMuitos(cobradores);

        List<Cobrador> cobradorsNoBanco = cobradorListarMapper.toEntity(cobradorServico.list());

        Long idBuscado = cobradorsNoBanco.get(cobradorsNoBanco.size() - 1).getId();
        mockMvc.perform(get(API_COBRADOR + idBuscado.toString()))
                .andExpect(status().isOk());

    }

}
