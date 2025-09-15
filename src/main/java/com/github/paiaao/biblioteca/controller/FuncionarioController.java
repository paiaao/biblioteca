package com.github.paiaao.biblioteca.controller;

import com.github.paiaao.biblioteca.model.Funcionario;
import com.github.paiaao.biblioteca.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/home")
    public String home(HttpSession session) {
        if (session.getAttribute("funcionarioLogado") == null) {
            return "redirect:/auth/login";
        }
        return "home";
    }

    @GetMapping("/form")
    public String mostrarForm(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        return "funcionarios/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
        return "redirect:/funcionarios/lista";
    }

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("funcionarios", funcionarioRepository.findAll());
        return "funcionarios/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        if (funcionario.isPresent()) {
            model.addAttribute("funcionario", funcionario.get());
            return "funcionarios/form"; // reaproveita formulario de cadastro para edição também
        } else {
            return "redirect:/funcionarios/lista";
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        funcionarioRepository.deleteById(id);
        return "redirect:/funcionarios/lista";
    }
}
