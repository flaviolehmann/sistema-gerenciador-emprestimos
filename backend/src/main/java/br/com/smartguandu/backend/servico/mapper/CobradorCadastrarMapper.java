package br.com.smartguandu.backend.servico.mapper;

import br.com.smartguandu.backend.dominio.Cobrador;
import br.com.smartguandu.backend.servico.dto.CobradorCadastrarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CobradorCadastrarMapper extends EntityMapper<CobradorCadastrarDTO, Cobrador> {
}
