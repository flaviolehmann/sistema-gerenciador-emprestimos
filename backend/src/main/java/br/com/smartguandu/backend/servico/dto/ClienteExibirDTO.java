package br.com.smartguandu.backend.servico.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteExibirDTO {
    private Long id;
    private String nome;
    private String email;
}
