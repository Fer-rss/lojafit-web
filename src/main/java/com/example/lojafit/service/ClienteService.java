package com.example.lojafit.service;

import com.example.lojafit.model.Cliente;
import com.example.lojafit.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    public Cliente salvar(Cliente cliente) {
        cliente.validar();
        return repository.save(cliente);
    }

    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = repository.findById(id);
        return cliente.orElseThrow(() ->
                new RuntimeException("Cliente não encontrado"));
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}