package com.github.paiaao.biblioteca.controller;

import com.github.paiaao.biblioteca.model.Cliente;
import com.github.paiaao.biblioteca.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;


    @GetMapping("/home")
    public String home(HttpSession session) {
        if (session.getAttribute("funcionarioLogado") == null) {
            return "redirect:/auth/login";
        }
        return "home";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        return "clientes/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/form";
    }

    @PostMapping
    public String salvar(Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow();
        model.addAttribute("cliente", cliente);
        return "clientes/form";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return "redirect:/clientes";
    }
}
