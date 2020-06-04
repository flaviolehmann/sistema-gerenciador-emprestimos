package br.com.smartguandu.backend.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
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
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long valor;

    @Column(name = "data_realizacao")
    private LocalDate dataRealizacao;

    @Column(name = "data_final_prevista")
    private LocalDate dataFinalPrevista;

    @Column(name = "data_final_real")
    private LocalDate dataFinalReal;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_agiota")
    private Agiota agiota;
}
