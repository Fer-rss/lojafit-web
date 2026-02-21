package com.example.lojafit.controller;

import com.example.lojafit.model.Venda;
import com.example.lojafit.repository.ClienteRepository;
import com.example.lojafit.repository.ProdutoRepository;
import com.example.lojafit.service.VendaService;
import com.example.lojafit.repository.VendaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vendas")
public class VendaController {

    private final VendaRepository vendaRepository;
    private final VendaService vendaService;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public VendaController(VendaRepository vendaRepository,
                           VendaService vendaService,
                           ClienteRepository clienteRepository,
                           ProdutoRepository produtoRepository) {
        this.vendaRepository = vendaRepository;
        this.vendaService = vendaService;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    // Listar vendas
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("vendas", vendaRepository.findAll());
        model.addAttribute("conteudo", "vendas/lista");
        model.addAttribute("titulo", "Vendas");
        return "layout/base";
    }

    // Nova venda
    @GetMapping("/form")
    public String novaVenda(Model model) {
        model.addAttribute("venda", new Venda());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("produtos", produtoRepository.findAll());
        model.addAttribute("conteudo", "vendas/form");
        model.addAttribute("titulo", "Nova Venda");
        return "layout/base";
    }

    // Salvar venda
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Venda venda) {
        vendaService.salvarVenda(venda);
        return "redirect:/vendas";
    }
}