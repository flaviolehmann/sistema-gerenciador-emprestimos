package br.com.smartguandu.backend.servico.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoListarDTO {

    private Long id;
    private Long valor;
    private Long idEmprestimo;
}
