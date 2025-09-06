package com.github.paiaao.biblioteca.controller;

import com.github.paiaao.biblioteca.model.Categoria;
import com.github.paiaao.biblioteca.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/funcionario/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listar(Model model) { //lista categorias
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "categorias/lista";
    }

    @GetMapping("/nova") //tela de cadastro de categoria
    public String novaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/form";
    }

    @PostMapping("/salvar") //salva categoria
    public String salvar(@ModelAttribute Categoria categoria) {
        categoriaService.salvar(categoria);
        return "redirect:/funcionario/categorias";
    }
    @GetMapping("/excluir/{id}") //exclui pelo id
    public String excluir(@PathVariable Long id) {
        categoriaService.excluir(id);
        return "redirect:/funcionario/categorias";
    }
    @GetMapping("/editar/{id}") //edita a categoria atraves da tela de cadastro reaproveitada
    public String editar(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.buscarPorId(id);
        model.addAttribute("categoria", categoria);
        return "categorias/form"; //mesmo form de cadastro reaproveitado pra edição
    }
}
