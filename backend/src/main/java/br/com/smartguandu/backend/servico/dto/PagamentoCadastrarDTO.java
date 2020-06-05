package br.com.smartguandu.backend.servico.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoCadastrarDTO {

    @NotNull
    private Long valor;

    @NotNull
    private Long idEmprestimo;
}
