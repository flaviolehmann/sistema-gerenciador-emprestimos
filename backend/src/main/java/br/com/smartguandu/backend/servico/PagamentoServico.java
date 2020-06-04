package br.com.smartguandu.backend.servico;

import br.com.smartguandu.backend.servico.dto.PagamentoCadastrarDTO;
import br.com.smartguandu.backend.servico.dto.PagamentoExibirDTO;
import br.com.smartguandu.backend.servico.dto.PagamentoListarDTO;

import java.util.List;

public interface PagamentoServico {

    PagamentoExibirDTO save(PagamentoCadastrarDTO emprestimoDTO);
    List<PagamentoListarDTO> list();
    PagamentoExibirDTO show(Long id);
    void delete(Long id);
}
