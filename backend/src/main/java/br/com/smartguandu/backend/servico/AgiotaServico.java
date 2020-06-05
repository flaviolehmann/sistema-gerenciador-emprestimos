package br.com.smartguandu.backend.servico;

import br.com.smartguandu.backend.servico.dto.AgiotaCadastrarDTO;
import br.com.smartguandu.backend.servico.dto.AgiotaEditarDTO;
import br.com.smartguandu.backend.servico.dto.AgiotaExibirDTO;
import br.com.smartguandu.backend.servico.dto.AgiotaListarDTO;

import java.util.List;

public interface AgiotaServico {

    AgiotaExibirDTO save(AgiotaCadastrarDTO agiotaDTO);
    List<AgiotaListarDTO> list();
    AgiotaExibirDTO show(Long id);
    AgiotaExibirDTO update(AgiotaEditarDTO agiotaDTO);
    void delete(Long id);
}
