package com.desafio.obras.service;

import com.desafio.obras.converter.AutorConverter;
import com.desafio.obras.dto.AutorDTO;
import com.desafio.obras.entity.Autor;
import com.desafio.obras.exceptions.ConflictException;
import com.desafio.obras.exceptions.ResourceNotFoundException;
import com.desafio.obras.repository.AutorRepository;
import com.desafio.obras.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;
    private final PasswordEncoder passwordEncoder;
    private final AutorConverter autorConverter;
    private final JwtUtil jwtUtil;

    public AutorDTO salvaAutor(AutorDTO autorDTO){
        emailExiste(autorDTO.getEmail());
        autorDTO.setSenha(passwordEncoder.encode(autorDTO.getSenha()));
        Autor autor = autorConverter.paraAutor(autorDTO);
        return autorConverter.paraAutorDTO(autorRepository.save(autor));
    }

    public void emailExiste(String email){
        try {
            boolean exite = verificaEmailExistente(email);
            if (exite) {
                throw new ConflictException("Email já cadastrado" + email);
            }
        } catch (ConflictException e){
            throw new ConflictException("Email já cadastrado" + e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email){

        return autorRepository.existsByEmail(email);
    }

    public AutorDTO buscaAutorPorEmail(String email){
        try {
            return autorConverter.paraAutorDTO(
                    autorRepository.findByEmail(email)
                            .orElseThrow(
                                    () -> new ResourceNotFoundException("Email não encontrado" + email)
                            )
            );
        } catch (ResourceNotFoundException e ){
            throw new ResourceNotFoundException("Email não encontrado" +email);
        }
    }

    public void deletaUsuarioPorEmail(String email){ autorRepository.deleteByEmail(email);}

    public AutorDTO atualizaDadosUsuario(String token, AutorDTO dto){
        //Busca de email do autor através do token (tira a obrigatoriedade do email)
        String email = jwtUtil.extractUsername(token.substring(7));

        //Criptografia de senha
        dto.setSenha(dto.getSenha()!=null ? passwordEncoder.encode(dto.getSenha()) : null);

        //Busca dos dados do autor no banco de dados
        Autor autorEntity = autorRepository.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException("Email não localizado"));
        //Mescla dos dados recebidos na requisição DTO com dados do DB
        Autor autor = autorConverter.updateAutor(dto, autorEntity);

        //Salva novos dados do autor convertido e depois converte para autorDTO
        return autorConverter.paraAutorDTO(autorRepository.save(autor));
    }

}
