package br.com.smartguandu.backend.servico.impl;

import br.com.smartguandu.backend.dominio.Pagamento;
import br.com.smartguandu.backend.repositorio.PagamentoRepositorio;
import br.com.smartguandu.backend.servico.PagamentoServico;
import br.com.smartguandu.backend.servico.dto.PagamentoCadastrarDTO;
import br.com.smartguandu.backend.servico.dto.PagamentoExibirDTO;
import br.com.smartguandu.backend.servico.dto.PagamentoListarDTO;
import br.com.smartguandu.backend.servico.exception.NotFoundException;
import br.com.smartguandu.backend.servico.mapper.PagamentoCadastrarMapper;
import br.com.smartguandu.backend.servico.mapper.PagamentoExibirMapper;
import br.com.smartguandu.backend.servico.mapper.PagamentoListarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PagamentoServicoImpl implements PagamentoServico {

    private final PagamentoRepositorio pagamentoRepositorio;
    private final PagamentoCadastrarMapper pagamentoCadastrarMapper;
    private final PagamentoExibirMapper pagamentoExibirMapper;
    private final PagamentoListarMapper pagamentoListarMapper;

    @Override
    public PagamentoExibirDTO save(PagamentoCadastrarDTO pagamentoDTO) {
        Pagamento pagamento = pagamentoCadastrarMapper.toEntity(pagamentoDTO);
        return pagamentoExibirMapper.toDto(pagamentoRepositorio.save(pagamento));
    }

    @Override
    public List<PagamentoListarDTO> list() {
        return pagamentoListarMapper.toDto(pagamentoRepositorio.findAll());
    }

    @Override
    public PagamentoExibirDTO show(Long id) {
        Optional<Pagamento> optionalPagamento = pagamentoRepositorio.findById(id);
        if (optionalPagamento.isPresent()) {
            return pagamentoExibirMapper.toDto(optionalPagamento.get());
        }
        throw new NotFoundException("Pagamento não encontrado no sistema.");
    }

    @Override
    public void delete(Long id) {
        Optional<Pagamento> optionalPagamento = pagamentoRepositorio.findById(id);
        if (optionalPagamento.isPresent()) {
            pagamentoRepositorio.deleteById(optionalPagamento.get().getId());
        }
        else {
            throw new NotFoundException("Pagamento não encontrado no sistema.");
        }
    }
}
