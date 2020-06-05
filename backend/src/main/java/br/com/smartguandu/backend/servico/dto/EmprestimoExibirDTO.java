package br.com.smartguandu.backend.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmprestimoExibirDTO {

    private Long id;
    private Long valor;
    private LocalDate dataRealizacao;
    private LocalDate dataFinalReal;
    private LocalDate dataFinalPrevista;
    private Long idCliente;
    private Long idAgiota;
}
