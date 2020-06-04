package br.com.smartguandu.backend.servico.impl;

import br.com.smartguandu.backend.dominio.Cobrador;
import br.com.smartguandu.backend.repositorio.CobradorRepositorio;
import br.com.smartguandu.backend.servico.CobradorServico;
import br.com.smartguandu.backend.servico.dto.CobradorCadastrarDTO;
import br.com.smartguandu.backend.servico.dto.CobradorEditarDTO;
import br.com.smartguandu.backend.servico.dto.CobradorExibirDTO;
import br.com.smartguandu.backend.servico.dto.CobradorListarDTO;
import br.com.smartguandu.backend.servico.exception.NotFoundException;
import br.com.smartguandu.backend.servico.mapper.CobradorCadastrarMapper;
import br.com.smartguandu.backend.servico.mapper.CobradorEditarMapper;
import br.com.smartguandu.backend.servico.mapper.CobradorExibirMapper;
import br.com.smartguandu.backend.servico.mapper.CobradorListarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CobradorServicoImpl implements CobradorServico {

    private final CobradorRepositorio cobradorRepositorio;
    private final CobradorCadastrarMapper cobradorCadastrarMapper;
    private final CobradorEditarMapper cobradorEditarMapper;
    private final CobradorExibirMapper cobradorExibirMapper;
    private final CobradorListarMapper cobradorListarMapper;

    @Override
    public CobradorExibirDTO save(CobradorCadastrarDTO cobradorDTO) {
        Cobrador cobradorSalvo = cobradorRepositorio.save(cobradorCadastrarMapper.toEntity(cobradorDTO));
        return cobradorExibirMapper.toDto(cobradorSalvo);
    }

    @Override
    public List<CobradorListarDTO> list() {
        return cobradorListarMapper.toDto(cobradorRepositorio.findAll());
    }

    @Override
    public CobradorExibirDTO show(Long id) {
        Optional<Cobrador> optionalCobrador = cobradorRepositorio.findById(id);
        if (optionalCobrador.isPresent()) {
            return cobradorExibirMapper.toDto(optionalCobrador.get());
        }
        throw new NotFoundException("Cobrador não encontrado no sistema.");
    }

    @Override
    public CobradorExibirDTO update(CobradorEditarDTO cobradorDTO) {
        Cobrador cobradorAtualizando = cobradorEditarMapper.toEntity(cobradorDTO);

        Optional<Cobrador> optionalCliente = cobradorRepositorio.findById(cobradorAtualizando.getId());
        if (optionalCliente.isPresent()) {
            cobradorRepositorio.save(cobradorAtualizando);
            return cobradorExibirMapper.toDto(cobradorAtualizando);
        }
        throw new NotFoundException("Cobrador não encontrado no sistema.");
    }

    @Override
    public void delete(Long id) {
        Optional<Cobrador> optionalCobrador = cobradorRepositorio.findById(id);
        if (optionalCobrador.isPresent()) {
            cobradorRepositorio.deleteById(optionalCobrador.get().getId());
        }
        else {
            throw new NotFoundException("Cobrador não encontrado no sistema.");
        }
    }
}
