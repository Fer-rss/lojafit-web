package com.example.lojafit.controller;

import com.example.lojafit.model.Fornecedor;
import com.example.lojafit.service.FornecedorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {

    private final FornecedorService service;

    public FornecedorController(FornecedorService service) {
        this.service = service;
    }

    // Listagem
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("fornecedores", service.listarTodos());
        model.addAttribute("conteudo", "fornecedores/lista");
        model.addAttribute("titulo", "Fornecedores");
        return "layout/base";
    }

    // Formulário novo
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("fornecedor", new Fornecedor());
        model.addAttribute("conteudo", "fornecedores/form");
        model.addAttribute("titulo", "Novo Fornecedor");
        return "layout/base";
    }

    // Salvar
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Fornecedor fornecedor) {
        service.salvar(fornecedor);
        return "redirect:/fornecedores";
    }

    // Editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("fornecedor", service.buscarPorId(id));
        model.addAttribute("conteudo", "fornecedores/form");
        model.addAttribute("titulo", "Editar Fornecedor");
        return "layout/base";
    }

    // Excluir
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/fornecedores";
    }
}
