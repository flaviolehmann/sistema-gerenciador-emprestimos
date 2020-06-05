package br.com.smartguandu.backend.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmprestimoEditarDTO {
    private Long id;
    private Long valor;
    private LocalDate dataFinalReal;
    private LocalDate dataFinalPrevista;
}
