package br.com.smartguandu.backend.servico.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmprestimoListarDTO {

    private Long id;
    private Long valor;
    private String nomeCliente;
    private String nomeAgiota;
}
