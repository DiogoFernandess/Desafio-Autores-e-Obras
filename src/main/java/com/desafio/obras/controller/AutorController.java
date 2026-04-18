package com.desafio.obras.controller;

import com.desafio.obras.dto.AutorDTO;
import com.desafio.obras.security.JwtUtil;
import com.desafio.obras.security.SecurityConfig;
import com.desafio.obras.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/autor")
@RequiredArgsConstructor
@Tag(name ="Autor" , description = "Cadastro e login de Autor de Obra")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class AutorController {

    private final AutorService autorService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    @Operation(summary = "Salvar Autor", description = "Cria um novo autor")
    public ResponseEntity<AutorDTO> salvaAutor (@RequestBody AutorDTO autorDTO){
        return ResponseEntity.ok(autorService.salvaAutor(autorDTO));
    }

    @PostMapping
    @Operation(summary = "Login de Autor" , description = "Login do usuário")
    public String login (@RequestBody AutorDTO autorDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(autorDTO.getEmail(), autorDTO.getSenha())
        );
        return "Bearer" + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    @Operation(summary = "Buscar dados de autor por email", description = "Buscar dados do autor")
    public ResponseEntity<AutorDTO> buscaUsuarioPorEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(autorService.buscaAutorPorEmail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email){
        autorService.deletaUsuarioPorEmail(email);
        return ResponseEntity.ok().build();
    }
}
