package br.com.smartguandu.backend.servico.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmprestimoCadastrarDTO {

    private Long valor;

    private LocalDate dataRealizacao;
    private LocalDate dataFinalReal;

    @NotNull
    private LocalDate dataFinalPrevista;

    @NotNull
    private Long idCliente;

    @NotNull
    private Long idAgiota;
}
