package com.desafio.obras.controller;

import com.desafio.obras.dto.AutorDTO;
import com.desafio.obras.security.SecurityConfig;
import com.desafio.obras.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autor")
@RequiredArgsConstructor
@Tag(name ="Autor" , description = "Cadastro e login de Autor de Obra")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class AutorController {

    private final AutorService autorService;

    @PostMapping
    @Operation(summary = "Salvar Usuários", description = "Cria um novo usuário")
    public ResponseEntity<AutorDTO> salvaAutor (@RequestBody AutorDTO autorDTO){
        return ResponseEntity.ok(autorService.salvaAutor(autorDTO));
    }
}
