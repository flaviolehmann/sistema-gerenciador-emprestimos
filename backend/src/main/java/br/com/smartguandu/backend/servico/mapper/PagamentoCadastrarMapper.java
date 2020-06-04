package br.com.smartguandu.backend.servico.mapper;

import br.com.smartguandu.backend.dominio.Pagamento;
import br.com.smartguandu.backend.servico.dto.PagamentoCadastrarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PagamentoCadastrarMapper extends EntityMapper<PagamentoCadastrarDTO, Pagamento> {

    @Override
    @Mapping(source = "idEmprestimo", target = "emprestimo.id")
    Pagamento toEntity(PagamentoCadastrarDTO pagamento);

    @Override
    @Mapping(source = "emprestimo.id", target = "idEmprestimo")
    PagamentoCadastrarDTO toDto(Pagamento pagamento);
}
