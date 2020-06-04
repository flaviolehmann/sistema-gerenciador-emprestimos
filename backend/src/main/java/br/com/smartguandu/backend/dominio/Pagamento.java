package br.com.smartguandu.backend.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long valor;
    private LocalDate dataPagamento = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "id_emprestimo")
    private Emprestimo emprestimo;

}
