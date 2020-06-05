package br.com.smartguandu.backend.repositorio;

import br.com.smartguandu.backend.dominio.Cobrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CobradorRepositorio extends JpaRepository<Cobrador, Long> {
}
