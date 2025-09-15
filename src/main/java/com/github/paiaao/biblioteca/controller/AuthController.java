package com.github.paiaao.biblioteca.controller;

import com.github.paiaao.biblioteca.model.Funcionario;
import com.github.paiaao.biblioteca.repository.FuncionarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "funcionarios/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String senha,
                        HttpSession session,
                        Model model) {

        Funcionario funcionario = funcionarioRepository.findByEmailAndSenha(email, senha);

        if (funcionario != null) {
            session.setAttribute("funcionarioLogado", funcionario);
            return "redirect:/home"; // envia para pag home
        }

        model.addAttribute("erro", "Email ou senha inválidos!");
        return "funcionarios/login"; //se estiver invalido retorna o erro
    }

    @GetMapping("/logout")   //deslogar do sistema /auth/login
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}
