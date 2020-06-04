package br.com.smartguandu.backend.servico.mapper;

import br.com.smartguandu.backend.dominio.Agiota;
import br.com.smartguandu.backend.servico.dto.AgiotaCadastrarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgiotaCadastrarMapper extends EntityMapper<AgiotaCadastrarDTO, Agiota> {
}
