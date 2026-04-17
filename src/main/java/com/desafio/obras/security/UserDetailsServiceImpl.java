package com.desafio.obras.security;

import com.desafio.obras.entity.Autor;
import com.desafio.obras.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    // Repositório para acessar dados de usuário no banco de dados
    @Autowired
    private AutorRepository autorRepository;

    // Implementação do método para carregar detalhes do usuário pelo e-mail

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca o usuário no banco de dados pelo e-mail
        Autor autor = autorRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Autor não encontrado: " + email));

        // Cria e retorna um objeto UserDetails com base no usuário encontrado
        return org.springframework.security.core.userdetails.User
                .withUsername(autor.getEmail()) // Define o nome de usuário como o e-mail
                .password(autor.getSenha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }
}
