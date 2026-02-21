package com.example.lojafit.controller;

import com.example.lojafit.model.Cliente;
import com.example.lojafit.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    // Listagem
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", service.listarTodos());
        model.addAttribute("fmt", new com.example.lojafit.util.FormatadorUtil());
        model.addAttribute("conteudo", "clientes/lista");
        model.addAttribute("titulo", "Clientes");
        return "layout/base";
    }

    // Formulário novo
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("conteudo", "clientes/form");
        model.addAttribute("titulo", "Novo Cliente");
        return "layout/base";

    }

    // Salvar
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Cliente cliente) {
        service.salvar(cliente);
        return "redirect:/clientes";
    }

    // Editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("cliente", service.buscarPorId(id));
        model.addAttribute("conteudo", "clientes/form");
        model.addAttribute("titulo", "Editar Cliente");
        return "layout/base";

    }

    // Excluir
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/clientes";
    }
} 