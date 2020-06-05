package br.com.smartguandu.backend.servico.mapper;

import br.com.smartguandu.backend.dominio.Cliente;
import br.com.smartguandu.backend.servico.dto.ClienteCadastrarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteCadastrarMapper extends EntityMapper<ClienteCadastrarDTO, Cliente> {
}
