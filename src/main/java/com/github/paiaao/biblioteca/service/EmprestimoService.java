package com.github.paiaao.biblioteca.service;

import com.github.paiaao.biblioteca.model.Cliente;
import com.github.paiaao.biblioteca.model.Emprestimo;
import com.github.paiaao.biblioteca.model.Livro;
import com.github.paiaao.biblioteca.repository.ClienteRepository;
import com.github.paiaao.biblioteca.repository.EmprestimoRepository;
import com.github.paiaao.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo buscarPorId(Long id) {
        return emprestimoRepository.findById(id).orElse(null);
    }

    public Emprestimo salvar(Emprestimo emprestimo, String nomeCliente) {
        // Busca cliente existente pelo nome
        Cliente cliente = clienteRepository.findByNome(nomeCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        emprestimo.setCliente(cliente);

        Livro livro = emprestimo.getLivro();

        // Validação de livro
        if (livro == null || !livroRepository.existsById(livro.getId())) {
            throw new IllegalArgumentException("Livro não encontrado para o empréstimo.");
        }

        // Impede empréstimo de livro já emprestado
        boolean jaEmprestado = emprestimoRepository.findAll().stream()
                .anyMatch(e -> e.getLivro().getId().equals(livro.getId()) && e.getDataDevolucao() == null);

        if (jaEmprestado) {
            throw new IllegalStateException("O livro já está emprestado e não pode ser emprestado novamente.");
        }

        return emprestimoRepository.save(emprestimo);
    }

    public void deletar(Long id) {
        emprestimoRepository.deleteById(id);
    }
}
