package br.com.smartguandu.backend.servico.mapper;

import br.com.smartguandu.backend.dominio.Emprestimo;
import br.com.smartguandu.backend.servico.dto.EmprestimoExibirDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmprestimoExibirMapper extends EntityMapper<EmprestimoExibirDTO, Emprestimo> {

    @Override
    @Mapping(source = "idCliente", target = "cliente.id")
    @Mapping(source = "idAgiota", target = "agiota.id")
    Emprestimo toEntity(EmprestimoExibirDTO emprestimo);

    @Override
    @Mapping(source = "cliente.id", target = "idCliente")
    @Mapping(source = "agiota.id", target = "idAgiota")
    EmprestimoExibirDTO toDto(Emprestimo emprestimo);

}
