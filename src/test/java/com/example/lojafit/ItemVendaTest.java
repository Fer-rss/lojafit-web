package com.example.lojafit;

import com.example.lojafit.model.Cliente;
import com.example.lojafit.model.ItemVenda;
import com.example.lojafit.model.Produto;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class ItemVendaTest {

    // CT01 — Calcular subtotal com valores válidos
    @Test
    void ct01_deveCalcularSubtotalCorretamente() {
        Produto produto = new Produto();
        produto.setPreco(new BigDecimal("10.00"));
        produto.setEstoque(10);

        ItemVenda item = new ItemVenda();
        item.setProduto(produto);
        item.setQuantidade(3);

        BigDecimal subtotal = item.calcularSubtotal();

        assertEquals(new BigDecimal("30.00"), subtotal);
    }

    // CT02 — Impedir cálculo com quantidade zero
    @Test
    void ct02_deveLancarExcecaoQuandoQuantidadeForZero() {
        Produto produto = new Produto();
        produto.setPreco(new BigDecimal("10.00"));
        produto.setEstoque(10);

        ItemVenda item = new ItemVenda();
        item.setProduto(produto);
        item.setQuantidade(0);

        IllegalStateException ex = assertThrows(
            IllegalStateException.class,
            item::calcularSubtotal
        );
        assertEquals("Quantidade inválida.", ex.getMessage());
    }

    // CT03 — Impedir preço negativo ou zero no produto
    @Test
    void ct03_deveLancarExcecaoQuandoPrecoForZeroOuNegativo() {
        Produto produtoPrecoZero = new Produto();
        produtoPrecoZero.setNomeProduto("Whey");
        produtoPrecoZero.setPreco(BigDecimal.ZERO);
        produtoPrecoZero.setEstoque(10);

        IllegalArgumentException exZero = assertThrows(
            IllegalArgumentException.class,
            produtoPrecoZero::validar
        );
        assertEquals("O preço deve ser maior que zero.", exZero.getMessage());

        Produto produtoPrecoNegativo = new Produto();
        produtoPrecoNegativo.setNomeProduto("Whey");
        produtoPrecoNegativo.setPreco(new BigDecimal("-5.00"));
        produtoPrecoNegativo.setEstoque(10);

        IllegalArgumentException exNegativo = assertThrows(
            IllegalArgumentException.class,
            produtoPrecoNegativo::validar
        );
        assertEquals("O preço deve ser maior que zero.", exNegativo.getMessage());
    }

    // CT04 — Validar campos obrigatórios do cliente (nome e CPF)
    @Test
    void ct04_deveLancarExcecaoQuandoNomeClienteForVazio() {
        Cliente cliente = new Cliente();
        cliente.setNomeCliente("");
        cliente.setCpf("12345678901");

        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            cliente::validar
        );
        assertEquals("O nome do cliente é obrigatório.", ex.getMessage());
    }

    @Test
    void ct04_deveLancarExcecaoQuandoCpfForInvalido() {
        Cliente cliente = new Cliente();
        cliente.setNomeCliente("João Silva");
        cliente.setCpf("123"); // CPF com menos de 11 dígitos

        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            cliente::validar
        );
        assertEquals("CPF inválido. Deve conter 11 dígitos.", ex.getMessage());
    }

    // CT06 — Impedir quantidade negativa
    @Test
    void ct06_deveLancarExcecaoQuandoQuantidadeForNegativa() {
        Produto produto = new Produto();
        produto.setNomeProduto("Creatina");
        produto.setPreco(new BigDecimal("50.00"));
        produto.setEstoque(10);

        ItemVenda item = new ItemVenda();
        item.setProduto(produto);
        item.setQuantidade(-1);
        item.setPrecoUnitario(produto.getPreco());

        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            item::validar
        );
        assertEquals("A quantidade deve ser maior que zero.", ex.getMessage());
    }
}
