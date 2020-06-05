package br.com.smartguandu.backend.servico.mapper;

import br.com.smartguandu.backend.dominio.Cobrador;
import br.com.smartguandu.backend.servico.dto.CobradorEditarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CobradorEditarMapper extends EntityMapper<CobradorEditarDTO, Cobrador> {
}
