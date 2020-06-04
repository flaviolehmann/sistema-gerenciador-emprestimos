package br.com.smartguandu.backend.servico.impl;

import br.com.smartguandu.backend.dominio.Cliente;
import br.com.smartguandu.backend.repositorio.ClienteRepositorio;
import br.com.smartguandu.backend.servico.ClienteServico;
import br.com.smartguandu.backend.servico.dto.ClienteCadastrarDTO;
import br.com.smartguandu.backend.servico.dto.ClienteEditarDTO;
import br.com.smartguandu.backend.servico.dto.ClienteExibirDTO;
import br.com.smartguandu.backend.servico.dto.ClienteListarDTO;
import br.com.smartguandu.backend.servico.exception.NotFoundException;
import br.com.smartguandu.backend.servico.mapper.ClienteCadastrarMapper;
import br.com.smartguandu.backend.servico.mapper.ClienteEditarMapper;
import br.com.smartguandu.backend.servico.mapper.ClienteExibirMapper;
import br.com.smartguandu.backend.servico.mapper.ClienteListarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClienteServicoImpl implements ClienteServico {

    private final ClienteRepositorio clienteRepositorio;
    private final ClienteCadastrarMapper clienteCadastrarMapper;
    private final ClienteEditarMapper clienteEditarMapper;
    private final ClienteExibirMapper clienteExibirMapper;
    private final ClienteListarMapper clienteListarMapper;

    @Override
    public ClienteExibirDTO save(ClienteCadastrarDTO clienteDTO) {
        Cliente clienteSalvo = clienteRepositorio.save(clienteCadastrarMapper.toEntity(clienteDTO));
        return clienteExibirMapper.toDto(clienteSalvo);
    }

    @Override
    public List<ClienteListarDTO> list() {
        return clienteListarMapper.toDto(clienteRepositorio.findAll());
    }

    @Override
    public ClienteExibirDTO show(Long id) {
        Optional<Cliente> optionalCliente = clienteRepositorio.findById(id);
        if (optionalCliente.isPresent()) {
            return clienteExibirMapper.toDto(optionalCliente.get());
        }
        throw new NotFoundException("Cliente não encontrado no sistema.");
    }

    @Override
    public ClienteExibirDTO update(ClienteEditarDTO clienteDTO) {
        Cliente clienteAtualizando = clienteEditarMapper.toEntity(clienteDTO);

        Optional<Cliente> optionalCliente = clienteRepositorio.findById(clienteAtualizando.getId());
        if (optionalCliente.isPresent()) {
            clienteRepositorio.save(clienteAtualizando);
            return clienteExibirMapper.toDto(clienteAtualizando);
        }
        throw new NotFoundException("Cliente não encontrado no sistema.");
    }

    @Override
    public void delete(Long id) {
        Optional<Cliente> optionalCliente = clienteRepositorio.findById(id);
        if (optionalCliente.isPresent()) {
            clienteRepositorio.deleteById(optionalCliente.get().getId());
        }
        else {
            throw new NotFoundException("Cliente não encontrado no sistema.");
        }
    }
}
