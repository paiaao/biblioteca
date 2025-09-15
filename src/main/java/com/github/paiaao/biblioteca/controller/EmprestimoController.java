package com.github.paiaao.biblioteca.controller;

import com.github.paiaao.biblioteca.model.Emprestimo;
import com.github.paiaao.biblioteca.repository.ClienteRepository;
import com.github.paiaao.biblioteca.repository.EmprestimoRepository;
import com.github.paiaao.biblioteca.repository.LivroRepository;
import com.github.paiaao.biblioteca.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.github.paiaao.biblioteca.model.Livro;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private EmprestimoService emprestimoService;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("emprestimos", emprestimoRepository.findAll());
        return "emprestimos/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro(new Livro());

        model.addAttribute("emprestimo", emprestimo);
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("livros", livroRepository.findAll());
        return "emprestimos/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Emprestimo emprestimo, @RequestParam String nomeCliente) {
        emprestimoService.salvar(emprestimo, nomeCliente);
        return "redirect:/emprestimos";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow();
        model.addAttribute("emprestimo", emprestimo);
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("livros", livroRepository.findAll());
        return "emprestimos/form";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        emprestimoRepository.deleteById(id);
        return "redirect:/emprestimos";
    }
}
