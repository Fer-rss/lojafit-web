package com.example.lojafit.controller;

import com.example.lojafit.repository.ProdutoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProdutoRepository produtoRepository;

    public HomeController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/home")
    public String home(Model model) {
        // Busca todos os produtos e limita aos 3 primeiros
        var produtos = produtoRepository.findAll()
                .stream()
                .limit(3)
                .toList();

        model.addAttribute("produtos", produtos);
        model.addAttribute("conteudo", "home");
        model.addAttribute("titulo", "Home");
        return "layout/base";
    }
}
