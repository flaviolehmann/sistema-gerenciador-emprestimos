package br.com.smartguandu.backend.repositorio;

import br.com.smartguandu.backend.dominio.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
}
