package br.com.smartguandu.backend.servico.mapper;

import br.com.smartguandu.backend.dominio.Cliente;
import br.com.smartguandu.backend.servico.dto.ClienteExibirDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteExibirMapper extends EntityMapper<ClienteExibirDTO, Cliente> {
}
