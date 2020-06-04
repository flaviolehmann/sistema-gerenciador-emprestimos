package br.com.smartguandu.backend.servico.mapper;

import br.com.smartguandu.backend.dominio.Emprestimo;
import br.com.smartguandu.backend.servico.dto.EmprestimoCadastrarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmprestimoCadastrarMapper extends EntityMapper<EmprestimoCadastrarDTO, Emprestimo> {

    @Override
    @Mapping(source = "idCliente", target = "cliente.id")
    @Mapping(source = "idAgiota", target = "agiota.id")
    Emprestimo toEntity(EmprestimoCadastrarDTO emprestimo);

    @Override
    @Mapping(source = "cliente.id", target = "idCliente")
    @Mapping(source = "agiota.id", target = "idAgiota")
    EmprestimoCadastrarDTO toDto(Emprestimo emprestimo);
}
