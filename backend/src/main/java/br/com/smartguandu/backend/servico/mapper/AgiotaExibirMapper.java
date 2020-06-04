package br.com.smartguandu.backend.servico.mapper;

import br.com.smartguandu.backend.dominio.Agiota;
import br.com.smartguandu.backend.servico.dto.AgiotaExibirDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgiotaExibirMapper extends EntityMapper<AgiotaExibirDTO, Agiota> {
}
