package br.com.smartguandu.backend.servico.impl;

import br.com.smartguandu.backend.dominio.Agiota;
import br.com.smartguandu.backend.repositorio.AgiotaRepositorio;
import br.com.smartguandu.backend.servico.AgiotaServico;
import br.com.smartguandu.backend.servico.dto.AgiotaCadastrarDTO;
import br.com.smartguandu.backend.servico.dto.AgiotaEditarDTO;
import br.com.smartguandu.backend.servico.dto.AgiotaExibirDTO;
import br.com.smartguandu.backend.servico.dto.AgiotaListarDTO;
import br.com.smartguandu.backend.servico.exception.NotFoundException;
import br.com.smartguandu.backend.servico.mapper.AgiotaCadastrarMapper;
import br.com.smartguandu.backend.servico.mapper.AgiotaEditarMapper;
import br.com.smartguandu.backend.servico.mapper.AgiotaExibirMapper;
import br.com.smartguandu.backend.servico.mapper.AgiotaListarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AgiotaServicoImpl implements AgiotaServico {

    private final AgiotaRepositorio agiotaRepositorio;
    private final AgiotaCadastrarMapper agiotaCadastrarMapper;
    private final AgiotaEditarMapper agiotaEditarMapper;
    private final AgiotaExibirMapper agiotaExibirMapper;
    private final AgiotaListarMapper agiotaListarMapper;

    @Override
    public AgiotaExibirDTO save(AgiotaCadastrarDTO agiotaDTO) {
        Agiota agiotaSalvo = agiotaRepositorio.save(agiotaCadastrarMapper.toEntity(agiotaDTO));
        return agiotaExibirMapper.toDto(agiotaSalvo);
    }

    @Override
    public List<AgiotaListarDTO> list() {
        return agiotaListarMapper.toDto(agiotaRepositorio.findAll());
    }

    @Override
    public AgiotaExibirDTO show(Long id) {
        Optional<Agiota> optionalAgiota = agiotaRepositorio.findById(id);
        if (optionalAgiota.isPresent()) {
            return agiotaExibirMapper.toDto(optionalAgiota.get());
        }
        throw new NotFoundException("Agiota não encontrado no sistema.");
    }

    @Override
    public AgiotaExibirDTO update(AgiotaEditarDTO agiotaDTO) {
        Agiota agiotaAtualizando = agiotaEditarMapper.toEntity(agiotaDTO);

        Optional<Agiota> optionalAgiota = agiotaRepositorio.findById(agiotaAtualizando.getId());
        if (optionalAgiota.isPresent()) {
            agiotaRepositorio.save(agiotaAtualizando);
            return agiotaExibirMapper.toDto(agiotaAtualizando);
        }
        throw new NotFoundException("Agiota não encontrado no sistema.");
    }

    @Override
    public void delete(Long id) {
        Optional<Agiota> optionalAgiota = agiotaRepositorio.findById(id);
        if (optionalAgiota.isPresent()) {
            agiotaRepositorio.deleteById(optionalAgiota.get().getId());
        }
        else {
            throw new NotFoundException("Agiota não encontrado no sistema.");
        }
    }
}
