package br.com.smartguandu.backend.servico.mapper;

import br.com.smartguandu.backend.dominio.Cliente;
import br.com.smartguandu.backend.servico.dto.ClienteEditarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteEditarMapper extends EntityMapper<ClienteEditarDTO, Cliente> {
}
