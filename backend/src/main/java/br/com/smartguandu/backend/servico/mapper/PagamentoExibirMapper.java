package br.com.smartguandu.backend.servico.mapper;

import br.com.smartguandu.backend.dominio.Pagamento;
import br.com.smartguandu.backend.servico.dto.PagamentoExibirDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PagamentoExibirMapper extends EntityMapper<PagamentoExibirDTO, Pagamento> {

    @Override
    @Mapping(source = "idEmprestimo", target = "emprestimo.id")
    Pagamento toEntity(PagamentoExibirDTO pagamento);

    @Override
    @Mapping(source = "emprestimo.id", target = "idEmprestimo")
    PagamentoExibirDTO toDto(Pagamento pagamento);
}
