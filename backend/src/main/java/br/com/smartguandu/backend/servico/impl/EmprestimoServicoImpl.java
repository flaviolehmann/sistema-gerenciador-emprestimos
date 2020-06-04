package br.com.smartguandu.backend.servico.impl;

import br.com.smartguandu.backend.dominio.Emprestimo;
import br.com.smartguandu.backend.repositorio.EmprestimoRepositorio;
import br.com.smartguandu.backend.servico.AgiotaServico;
import br.com.smartguandu.backend.servico.ClienteServico;
import br.com.smartguandu.backend.servico.EmprestimoServico;
import br.com.smartguandu.backend.servico.dto.EmprestimoCadastrarDTO;
import br.com.smartguandu.backend.servico.dto.EmprestimoEditarDTO;
import br.com.smartguandu.backend.servico.dto.EmprestimoExibirDTO;
import br.com.smartguandu.backend.servico.dto.EmprestimoListarDTO;
import br.com.smartguandu.backend.servico.exception.NotFoundException;
import br.com.smartguandu.backend.servico.mapper.EmprestimoCadastrarMapper;
import br.com.smartguandu.backend.servico.mapper.EmprestimoEditarMapper;
import br.com.smartguandu.backend.servico.mapper.EmprestimoExibirMapper;
import br.com.smartguandu.backend.servico.mapper.EmprestimoListarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmprestimoServicoImpl implements EmprestimoServico {

    private final EmprestimoRepositorio emprestimoRepositorio;
    private final EmprestimoCadastrarMapper emprestimoCadastrarMapper;
    private final EmprestimoEditarMapper emprestimoEditarMapper;
    private final EmprestimoExibirMapper emprestimoExibirMapper;
    private final EmprestimoListarMapper emprestimoListarMapper;

    private final ClienteServico clienteServico;
    private final AgiotaServico agiotaServico;

    @Override
    public EmprestimoExibirDTO save(EmprestimoCadastrarDTO emprestimoDTO) {
        Emprestimo emprestimo = emprestimoCadastrarMapper.toEntity(emprestimoDTO);
        emprestimo.setDataRealizacao(LocalDate.now());
        return emprestimoExibirMapper.toDto(emprestimoRepositorio.save(emprestimo));
    }

    @Override
    public List<EmprestimoListarDTO> list() {
        return emprestimoListarMapper.toDto(emprestimoRepositorio.findAll());
    }

    @Override
    public EmprestimoExibirDTO show(Long id) {
        Optional<Emprestimo> optionalEmprestimo = emprestimoRepositorio.findById(id);
        if (optionalEmprestimo.isPresent()) {
            return emprestimoExibirMapper.toDto(optionalEmprestimo.get());
        }
        throw new NotFoundException("Empréstimo não encontrado no sistema.");
    }

    @Override
    public EmprestimoExibirDTO update(EmprestimoEditarDTO emprestimoDTO) {
        Optional<Emprestimo> emprestimoAtualizando = emprestimoRepositorio.findById(emprestimoEditarMapper.toEntity(emprestimoDTO).getId());
        if (emprestimoAtualizando.isPresent()) {
            Emprestimo emprestimo = emprestimoAtualizando.get();
            emprestimo.setDataRealizacao(LocalDate.now());
            emprestimoRepositorio.save(emprestimo);
            return emprestimoExibirMapper.toDto(emprestimo);
        }
        throw new NotFoundException("Empréstimo não encontrado no sistema.");
    }

    @Override
    public void delete(Long id) {
        Optional<Emprestimo> optionalEmprestimo = emprestimoRepositorio.findById(id);
        if (optionalEmprestimo.isPresent()) {
            emprestimoRepositorio.deleteById(optionalEmprestimo.get().getId());
        }
        else {
            throw new NotFoundException("Empréstimo não encontrado no sistema.");
        }
    }
}
