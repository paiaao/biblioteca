package com.github.paiaao.biblioteca.service;

import com.github.paiaao.biblioteca.model.Categoria;
import com.github.paiaao.biblioteca.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public void salvar(Categoria categoria) {
        categoriaRepository.save(categoria);
    }
    public void excluir(Long id) {
        categoriaRepository.deleteById(id);
    }
    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria inválida: " + id));
    }
}