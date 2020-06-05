package br.com.smartguandu.backend.builder;

import br.com.smartguandu.backend.dominio.Cobrador;
import br.com.smartguandu.backend.servico.CobradorServico;
import br.com.smartguandu.backend.servico.mapper.CobradorCadastrarMapper;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CobradorBuilder {

    private final CobradorServico cobradorServico;
    private final CobradorCadastrarMapper cobradorCadastrarMapper;

    private Long id = 0L;

    public Cobrador build() {
        Cobrador cobrador = new Cobrador();

        cobrador.setEmail("flavio" + id.toString() + "@gmail.com");
        cobrador.setNome("Flávio " + id.toString());
        cobrador.setSenha("123456" + id.toString());
        id += 1;

        return cobrador;
    }

    public List<Cobrador> buildMany(int qtde, List<Cobrador> cobradores) {
        if (qtde < 1) return cobradores;

        cobradores.add(build());
        return buildMany(qtde - 1, cobradores);
    }

    public List<Cobrador> buildMany(int qtde) {
        return buildMany(qtde, new ArrayList<>());
    }

    public void persistir(Cobrador cobrador) {
        cobradorServico.save(cobradorCadastrarMapper.toDto(cobrador));
    }

    public void persistirMuitos(List<Cobrador> cobradores) {
        cobradores.forEach(cobrador -> cobradorServico.save(cobradorCadastrarMapper.toDto(cobrador)));
    }

}
