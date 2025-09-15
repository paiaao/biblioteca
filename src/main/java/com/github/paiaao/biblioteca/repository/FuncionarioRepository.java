package com.github.paiaao.biblioteca.repository;

import com.github.paiaao.biblioteca.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Funcionario findByEmailAndSenha(String email, String senha);
}
