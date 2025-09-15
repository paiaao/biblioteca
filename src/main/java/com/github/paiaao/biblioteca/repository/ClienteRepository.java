package com.github.paiaao.biblioteca.repository;

import com.github.paiaao.biblioteca.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByNome(String nome);
}
