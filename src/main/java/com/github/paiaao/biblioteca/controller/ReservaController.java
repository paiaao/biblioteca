package com.github.paiaao.biblioteca.controller;

import com.github.paiaao.biblioteca.model.Reserva;
import com.github.paiaao.biblioteca.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("reservas", reservaRepository.findAll());
        return "reservas/lista";
    }

    @GetMapping("/nova")
    public String novoForm(Model model) {
        model.addAttribute("reserva", new Reserva());
        return "reservas/form";
    }

    @PostMapping
    public String salvar(Reserva reserva) {
        reservaRepository.save(reserva);
        return "redirect:/reservas";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow();
        model.addAttribute("reserva", reserva);
        return "reservas/form";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        reservaRepository.deleteById(id);
        return "redirect:/reservas";
    }
}
