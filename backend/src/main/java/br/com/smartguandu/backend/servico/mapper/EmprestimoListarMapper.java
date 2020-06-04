package br.com.smartguandu.backend.servico.mapper;

import br.com.smartguandu.backend.dominio.Emprestimo;
import br.com.smartguandu.backend.servico.dto.EmprestimoListarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmprestimoListarMapper extends EntityMapper<EmprestimoListarDTO, Emprestimo> {

    @Override
    @Mapping(source = "agiota.nome", target = "nomeAgiota")
    @Mapping(source = "cliente.nome", target = "nomeCliente")
    EmprestimoListarDTO toDto(Emprestimo emprestimo);

    @Override
    @Mapping(source = "nomeAgiota", target = "agiota.nome")
    @Mapping(source = "nomeCliente", target = "cliente.nome")
    Emprestimo toEntity(EmprestimoListarDTO emprestimo);

}
