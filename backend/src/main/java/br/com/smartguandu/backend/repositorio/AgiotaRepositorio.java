package br.com.smartguandu.backend.repositorio;

import br.com.smartguandu.backend.dominio.Agiota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgiotaRepositorio extends JpaRepository<Agiota, Long> {
}
