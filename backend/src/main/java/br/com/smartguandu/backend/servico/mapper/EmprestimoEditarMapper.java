package br.com.smartguandu.backend.servico.mapper;

import br.com.smartguandu.backend.dominio.Emprestimo;
import br.com.smartguandu.backend.servico.dto.EmprestimoEditarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmprestimoEditarMapper extends EntityMapper<EmprestimoEditarDTO, Emprestimo> {
}
