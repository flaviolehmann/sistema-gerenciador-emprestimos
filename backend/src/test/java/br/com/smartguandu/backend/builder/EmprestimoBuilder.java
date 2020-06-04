package br.com.smartguandu.backend.builder;

import br.com.smartguandu.backend.dominio.Agiota;
import br.com.smartguandu.backend.dominio.Cliente;
import br.com.smartguandu.backend.dominio.Emprestimo;
import br.com.smartguandu.backend.servico.EmprestimoServico;
import br.com.smartguandu.backend.servico.mapper.AgiotaExibirMapper;
import br.com.smartguandu.backend.servico.mapper.ClienteExibirMapper;
import br.com.smartguandu.backend.servico.mapper.EmprestimoCadastrarMapper;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class EmprestimoBuilder {

    private final EmprestimoServico emprestimoServico;
    private final EmprestimoCadastrarMapper emprestimoCadastrarMapper;
    private final ClienteBuilder clienteBuilder;
    private final AgiotaBuilder agiotaBuilder;
    private final ClienteExibirMapper clienteExibirMapper;
    private final AgiotaExibirMapper agiotaExibirMapper;

    private Long id = 0L;

    public Emprestimo build() {
        Emprestimo emprestimo = new Emprestimo();

        emprestimo.setValor(1200L);
        emprestimo.setDataFinalPrevista(LocalDate.now().plusYears(1));

        Cliente cliente = clienteBuilder.build();
        emprestimo.setCliente(clienteExibirMapper.toEntity(clienteBuilder.persistir(cliente)));

        Agiota agiota = agiotaBuilder.build();
        emprestimo.setAgiota(agiotaExibirMapper.toEntity(agiotaBuilder.persistir(agiota)));

        id += 1;
        return emprestimo;
    }

    public List<Emprestimo> buildMany(int qtde, List<Emprestimo> emprestimos) {
        if (qtde < 1) return emprestimos;

        emprestimos.add(build());
        return buildMany(qtde - 1, emprestimos);
    }

    public List<Emprestimo> buildMany(int qtde) {
        return buildMany(qtde, new ArrayList<>());
    }

    public void persistir(Emprestimo emprestimo) {
        emprestimoServico.save(emprestimoCadastrarMapper.toDto(emprestimo));
    }

    public void persistirMuitos(List<Emprestimo> emprestimos) {
        emprestimos.forEach(emprestimo -> emprestimoServico.save(emprestimoCadastrarMapper.toDto(emprestimo)));
    }

}
