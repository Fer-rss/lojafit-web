package com.example.lojafit.service;

import com.example.lojafit.model.ItemVenda;
import com.example.lojafit.model.Venda;
import com.example.lojafit.repository.VendaRepository;
import com.example.lojafit.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;

    public VendaService(VendaRepository vendaRepository,
                        ProdutoRepository produtoRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Venda salvarVenda(Venda venda) {

        if (venda.getItens() == null || venda.getItens().isEmpty()) {
            throw new IllegalArgumentException("A venda deve conter ao menos um item.");
        }

        BigDecimal total = BigDecimal.ZERO;

        for (ItemVenda item : venda.getItens()) {

            item.validar();

            // Busca produto real do banco
            var produto = produtoRepository.findById(
                    item.getProduto().getId()
            ).orElseThrow(() ->
                    new IllegalArgumentException("Produto não encontrado")
            );

            // Verifica estoque
            if (produto.getEstoque() < item.getQuantidade()) {
                throw new IllegalArgumentException(
                        "Estoque insuficiente para o produto: " + produto.getNomeProduto()
                );
            }

            // Atualiza estoque
            produto.setEstoque(produto.getEstoque() - item.getQuantidade());
            produtoRepository.save(produto);

            // Atualiza preço real
            item.setPrecoUnitario(produto.getPreco());

            BigDecimal subtotal = item.getSubtotal();
            total = total.add(subtotal);

            item.setVenda(venda);
        }

        venda.setTotal(total);

        return vendaRepository.save(venda);
    }
}   