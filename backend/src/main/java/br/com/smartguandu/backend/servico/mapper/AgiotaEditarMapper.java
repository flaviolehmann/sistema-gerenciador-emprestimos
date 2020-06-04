package br.com.smartguandu.backend.servico.mapper;

import br.com.smartguandu.backend.dominio.Agiota;
import br.com.smartguandu.backend.servico.dto.AgiotaEditarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgiotaEditarMapper extends EntityMapper<AgiotaEditarDTO, Agiota> {
}
