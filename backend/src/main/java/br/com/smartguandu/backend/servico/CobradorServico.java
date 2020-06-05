package br.com.smartguandu.backend.servico;

import br.com.smartguandu.backend.servico.dto.CobradorCadastrarDTO;
import br.com.smartguandu.backend.servico.dto.CobradorEditarDTO;
import br.com.smartguandu.backend.servico.dto.CobradorExibirDTO;
import br.com.smartguandu.backend.servico.dto.CobradorListarDTO;

import java.util.List;

public interface CobradorServico {

    CobradorExibirDTO save(CobradorCadastrarDTO cobradorDTO);
    List<CobradorListarDTO> list();
    CobradorExibirDTO show(Long id);
    CobradorExibirDTO update(CobradorEditarDTO cobradorDTO);
    void delete(Long id);
}
