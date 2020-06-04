package br.com.smartguandu.backend.builder;

import br.com.smartguandu.backend.dominio.Agiota;
import br.com.smartguandu.backend.servico.AgiotaServico;
import br.com.smartguandu.backend.servico.mapper.AgiotaCadastrarMapper;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class AgiotaBuilder {

    private final AgiotaServico agiotaServico;
    private final AgiotaCadastrarMapper agiotaCadastrarMapper;

    private Long id = 0L;

    public Agiota build() {
        Agiota agiota = new Agiota();

        agiota.setEmail("flavio" + id.toString() + "@gmail.com");
        agiota.setNome("Flávio " + id.toString());
        agiota.setSenha("123456" + id.toString());
        id += 1;

        return agiota;
    }

    public List<Agiota> buildMany(int qtde, List<Agiota> agiotas) {
        if (qtde < 1) return agiotas;

        agiotas.add(build());
        return buildMany(qtde - 1, agiotas);
    }

    public List<Agiota> buildMany(int qtde) {
        return buildMany(qtde, new ArrayList<>());
    }

    public void persistir(Agiota agiota) {
        agiotaServico.save(agiotaCadastrarMapper.toDto(agiota));
    }

    public void persistirMuitos(List<Agiota> agiotas) {
        agiotas.forEach(agiota -> agiotaServico.save(agiotaCadastrarMapper.toDto(agiota)));
    }

}
