package com.desafio.obras.repository;


import com.desafio.obras.entity.Autor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    boolean existsByEmail(String email);

    Optional<Autor> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);
}
