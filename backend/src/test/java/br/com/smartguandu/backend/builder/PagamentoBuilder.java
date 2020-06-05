package br.com.smartguandu.backend.builder;

import br.com.smartguandu.backend.dominio.Emprestimo;
import br.com.smartguandu.backend.dominio.Pagamento;
import br.com.smartguandu.backend.servico.EmprestimoServico;
import br.com.smartguandu.backend.servico.PagamentoServico;
import br.com.smartguandu.backend.servico.dto.PagamentoExibirDTO;
import br.com.smartguandu.backend.servico.mapper.AgiotaExibirMapper;
import br.com.smartguandu.backend.servico.mapper.ClienteExibirMapper;
import br.com.smartguandu.backend.servico.mapper.EmprestimoCadastrarMapper;
import br.com.smartguandu.backend.servico.mapper.EmprestimoExibirMapper;
import br.com.smartguandu.backend.servico.mapper.PagamentoCadastrarMapper;

import java.util.ArrayList;
import java.util.List;

public class PagamentoBuilder {

    private final PagamentoServico pagamentoServico;
    private final PagamentoCadastrarMapper pagamentoCadastrarMapper;

    private Long id = 0L;
    private Emprestimo emprestimo;

    public PagamentoBuilder(EmprestimoServico emprestimoServico,
                            EmprestimoCadastrarMapper emprestimoCadastrarMapper,
                            ClienteBuilder clienteBuilder,
                            AgiotaBuilder agiotaBuilder,
                            ClienteExibirMapper clienteExibirMapper,
                            AgiotaExibirMapper agiotaExibirMapper,
                            PagamentoServico pagamentoServico,
                            PagamentoCadastrarMapper pagamentoCadastrarMapper,
                            EmprestimoExibirMapper emprestimoExibirMapper) {
        this.pagamentoServico = pagamentoServico;
        this.pagamentoCadastrarMapper = pagamentoCadastrarMapper;

        EmprestimoBuilder emprestimoBuilder = new EmprestimoBuilder(
                emprestimoServico, emprestimoCadastrarMapper, clienteBuilder,
                agiotaBuilder, clienteExibirMapper, agiotaExibirMapper);
        this.emprestimo = emprestimoExibirMapper.toEntity(emprestimoServico.save(emprestimoCadastrarMapper.toDto(emprestimoBuilder.build())));
    }

    public Pagamento build() {
        Pagamento pagamento = new Pagamento();
        pagamento.setValor(1200L);
        pagamento.setEmprestimo(emprestimo);

        id += 1;
        return pagamento;
    }

    public List<Pagamento> buildMany(int qtde, List<Pagamento> emprestimos) {
        if (qtde < 1) return emprestimos;
        emprestimos.add(build());
        return buildMany(qtde - 1, emprestimos);
    }

    public List<Pagamento> buildMany(int qtde) {
        return buildMany(qtde, new ArrayList<>());
    }

    public PagamentoExibirDTO persistir(Pagamento pagamento) {
        return pagamentoServico.save(pagamentoCadastrarMapper.toDto(pagamento));
    }

    public void persistirMuitos(List<Pagamento> pagamentos) {
        pagamentos.forEach(pagamento -> pagamentoServico.save(pagamentoCadastrarMapper.toDto(pagamento)));
    }
}
