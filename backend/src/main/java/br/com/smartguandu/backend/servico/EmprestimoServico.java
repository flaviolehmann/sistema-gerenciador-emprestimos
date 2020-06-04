package br.com.smartguandu.backend.servico;

import br.com.smartguandu.backend.servico.dto.EmprestimoCadastrarDTO;
import br.com.smartguandu.backend.servico.dto.EmprestimoEditarDTO;
import br.com.smartguandu.backend.servico.dto.EmprestimoExibirDTO;
import br.com.smartguandu.backend.servico.dto.EmprestimoListarDTO;

import java.util.List;

public interface EmprestimoServico {

    EmprestimoExibirDTO save(EmprestimoCadastrarDTO emprestimoDTO);
    List<EmprestimoListarDTO> list();
    EmprestimoExibirDTO show(Long id);
    EmprestimoExibirDTO update(EmprestimoEditarDTO emprestimoDTO);
    void delete(Long id);
}
