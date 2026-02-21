
package com.example.lojafit.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**@author Fernando */

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_produto", nullable = false, length = 100)
    private String nomeProduto;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @Column(name = "categoria", length = 50)
    private String categoria;

    @Column(name = "observacao", length = 255)
    private String observacao;

    @Column(name = "preco", precision = 10, scale = 2, nullable = false)
    private BigDecimal preco;

    @Column(name = "estoque", nullable = false)
    private int estoque;

    // Construtor
    public Produto() {}
    
     // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setEstoque(int estoque) {    
        this.estoque = estoque;
    }
   
    // Getters
    public Long getId() {
        return id; }

    public String getNomeProduto() {
        return nomeProduto; }

    public Fornecedor getFornecedor() {
        return fornecedor; }

    public String getCategoria() {
        return categoria; }

    public String getObservacao() {
        return observacao; }

    public BigDecimal getPreco() {
        return preco; }

    public int getEstoque() {    
        return estoque; }

    // --------- Validação ---------
    public void validar() {
        if (nomeProduto == null || nomeProduto.isBlank()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }
        if (preco == null || preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }
        if (estoque < 0) {
            throw new IllegalArgumentException("Quantidade em estoque não pode ser negativa.");
        }
    }
    
    @Override
    public String toString() {
        return nomeProduto;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return id != null && id.equals(produto.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
