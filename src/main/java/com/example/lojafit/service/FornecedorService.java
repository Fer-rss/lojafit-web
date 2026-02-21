package com.example.lojafit.service;

import com.example.lojafit.model.Fornecedor;
import com.example.lojafit.repository.FornecedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    private final FornecedorRepository repository;

    public FornecedorService(FornecedorRepository repository) {
        this.repository = repository;
    }

    public List<Fornecedor> listarTodos() {
        return repository.findAll();
    }

    public Fornecedor salvar(Fornecedor fornecedor) {
        fornecedor.validar();
        return repository.save(fornecedor);
    }

    public Fornecedor buscarPorId(Long id) {
        Optional<Fornecedor> fornecedor = repository.findById(id);
        return fornecedor.orElseThrow(() ->
                new RuntimeException("Fornecedor não encontrado"));
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
