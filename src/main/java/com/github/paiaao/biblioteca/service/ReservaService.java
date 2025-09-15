package com.github.paiaao.biblioteca.service;

import com.github.paiaao.biblioteca.model.Reserva;
import com.github.paiaao.biblioteca.model.Livro;
import com.github.paiaao.biblioteca.repository.ReservaRepository;
import com.github.paiaao.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private LivroRepository livroRepository;

    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }

    public Reserva buscarPorId(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    public Reserva salvar(Reserva reserva) {
        Livro livro = reserva.getLivro();

        // checa se o livro existe
        if (livro == null || !livroRepository.existsById(livro.getId())) {
            throw new IllegalArgumentException("Livro não encontrado para reserva.");
        }

        // não deixa mais de uma reserva no mesmo livro pelo mesmo cliente
        boolean jaReservado = reservaRepository.findAll().stream()
                .anyMatch(r -> r.getLivro().getId().equals(livro.getId())
                        && r.getCliente().getId().equals(reserva.getCliente().getId()));

        if (jaReservado) {
            throw new IllegalStateException("Este cliente já reservou este livro.");
        }

        return reservaRepository.save(reserva);
    }

    public void deletar(Long id) {
        reservaRepository.deleteById(id);
    }
}
