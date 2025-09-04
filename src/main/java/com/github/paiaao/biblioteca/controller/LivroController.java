package com.github.paiaao.biblioteca.controller;

import com.github.paiaao.biblioteca.model.Livro;
import com.github.paiaao.biblioteca.service.CategoriaService;
import com.github.paiaao.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/funcionario/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("listaLivros", livroService.listarTodos());
        return "funcionario/livros/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("livro", new Livro());
        model.addAttribute("listaCategorias", categoriaService.listarTodas());
        return "funcionario/livros/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Livro livro, RedirectAttributes ra) {
        // Lógica de negócio: Se for um novo livro, a quantidade disponível é igual ao estoque
        if (livro.getId() == null) {
            livro.setQuantidadeDisponivel(livro.getQuantidadeEstoque());
        }
        livroService.salvar(livro);
        ra.addFlashAttribute("mensagem", "Livro salvo com sucesso!");
        return "redirect:/funcionario/livros";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("livro", livroService.buscarPorId(id).orElse(null));
        model.addAttribute("listaCategorias", categoriaService.listarTodas());
        return "funcionario/livros/form";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes ra) {
        livroService.deletar(id);
        ra.addFlashAttribute("mensagem", "Livro deletado com sucesso!");
        return "redirect:/funcionario/livros";
    }
}
