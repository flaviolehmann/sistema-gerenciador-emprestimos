package br.com.smartguandu.backend.servico.mapper;

import br.com.smartguandu.backend.dominio.Pagamento;
import br.com.smartguandu.backend.servico.dto.PagamentoListarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PagamentoListarMapper extends EntityMapper<PagamentoListarDTO, Pagamento> {

    @Override
    @Mapping(source = "idEmprestimo", target = "emprestimo.id")
    Pagamento toEntity(PagamentoListarDTO pagamento);

    @Override
    @Mapping(source = "emprestimo.id", target = "idEmprestimo")
    PagamentoListarDTO toDto(Pagamento pagamento);
}
