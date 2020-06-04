package br.com.smartguandu.backend.servico.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CobradorCadastrarDTO {
    @NotNull
    @Size(min = 3, max = 200)
    private String nome;

    @NotNull
    @Email
    @Size(max = 100)
    private String email;

    @NotNull
    @Size(min = 5, max = 20)
    private String senha;
}
