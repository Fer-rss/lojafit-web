package com.example.lojafit.controller;

import com.example.lojafit.model.Produto;
import com.example.lojafit.service.ProdutoService;
import java.math.BigDecimal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    // LISTA
    @GetMapping
     public String listar(Model model) {
         model.addAttribute("produtos", service.listarTodos());
         model.addAttribute("conteudo", "produtos/lista");
         model.addAttribute("titulo", "Produtos");
         return "layout/base";
     }

    // FORMULÁRIO NOVO
    @GetMapping("/novo")
    public String novoProduto(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("conteudo", "produtos/form");
        model.addAttribute("titulo", "Novo Produto");
        return "layout/base";
    }

    // SALVAR
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Produto produto,
                         @RequestParam("preco") String precoStr) {
        try {
            String precoLimpo = precoStr
                    .replace("R$", "")
                    .replace(" ", "")
                    .trim();
            if (precoLimpo.contains(",")) {
                precoLimpo = precoLimpo
                        .replace(".", "")
                        .replace(",", ".");
            }
            produto.setPreco(new BigDecimal(precoLimpo));
        } catch (NumberFormatException e) {
            produto.setPreco(BigDecimal.ZERO);
        }
        service.salvar(produto);
        return "redirect:/produtos";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Produto produto = service.buscarPorId(id);
        model.addAttribute("produto", produto);
        model.addAttribute("conteudo", "produtos/form");
        model.addAttribute("titulo", "Editar Produto");
        return "layout/base";
    }

    // EXCLUIR
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/produtos";
    }
}