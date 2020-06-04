package br.com.smartguandu.backend.servico.mapper;

import br.com.smartguandu.backend.dominio.Agiota;
import br.com.smartguandu.backend.servico.dto.AgiotaListarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgiotaListarMapper extends EntityMapper<AgiotaListarDTO, Agiota> {
}
