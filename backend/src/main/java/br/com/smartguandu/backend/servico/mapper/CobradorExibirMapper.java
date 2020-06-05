package br.com.smartguandu.backend.servico.mapper;

import br.com.smartguandu.backend.dominio.Cobrador;
import br.com.smartguandu.backend.servico.dto.CobradorExibirDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CobradorExibirMapper extends EntityMapper<CobradorExibirDTO, Cobrador> {
}
