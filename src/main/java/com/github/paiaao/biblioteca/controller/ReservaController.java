package com.github.paiaao.biblioteca.controller;

import com.github.paiaao.biblioteca.model.Reserva;
import com.github.paiaao.biblioteca.repository.ClienteRepository;
import com.github.paiaao.biblioteca.repository.LivroRepository;
import com.github.paiaao.biblioteca.repository.ReservaRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/home")
    public String home(HttpSession session) {
        if (session.getAttribute("funcionarioLogado") == null) {
            return "redirect:/auth/login";
        }
        return "home";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("reservas", reservaRepository.findAll());
        return "reservas/lista";
    }

    @GetMapping("/nova")
    public String novoForm(Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("livros", livroRepository.findAll());
        return "reservas/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Reserva reserva) {
        reservaRepository.save(reserva);
        return "redirect:/reservas";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow();
        model.addAttribute("reserva", reserva);
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("livros", livroRepository.findAll());
        return "reservas/form";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        reservaRepository.deleteById(id);
        return "redirect:/reservas";
    }
}
