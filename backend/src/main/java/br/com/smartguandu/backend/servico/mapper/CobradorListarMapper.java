package br.com.smartguandu.backend.servico.mapper;

import br.com.smartguandu.backend.dominio.Cobrador;
import br.com.smartguandu.backend.servico.dto.CobradorListarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CobradorListarMapper extends EntityMapper<CobradorListarDTO, Cobrador> {
}
