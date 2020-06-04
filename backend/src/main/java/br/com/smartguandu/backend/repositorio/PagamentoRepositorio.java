package br.com.smartguandu.backend.repositorio;

import br.com.smartguandu.backend.dominio.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepositorio extends JpaRepository<Pagamento, Long> {
}
