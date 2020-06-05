package br.com.smartguandu.backend.servico;

import br.com.smartguandu.backend.servico.dto.ClienteCadastrarDTO;
import br.com.smartguandu.backend.servico.dto.ClienteEditarDTO;
import br.com.smartguandu.backend.servico.dto.ClienteExibirDTO;
import br.com.smartguandu.backend.servico.dto.ClienteListarDTO;

import java.util.List;

public interface ClienteServico {

    ClienteExibirDTO save(ClienteCadastrarDTO clienteDTO);
    List<ClienteListarDTO> list();
    ClienteExibirDTO show(Long id);
    ClienteExibirDTO update(ClienteEditarDTO clienteDTO);
    void delete(Long id);
}
