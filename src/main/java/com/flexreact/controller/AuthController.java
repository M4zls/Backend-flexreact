package com.flexreact.controller;

import com.flexreact.dto.AuthResponse;
import com.flexreact.dto.LoginRequest;
import com.flexreact.dto.RegisterRequest;
import com.flexreact.dto.UsuarioResponse;
import com.flexreact.entity.Usuario;
import com.flexreact.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registrar(@Valid @RequestBody RegisterRequest request) {
        try {
            AuthResponse response = authService.registrar(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).build();
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        // JWT es stateless, no necesitamos hacer nada en el backend
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/me")
    public ResponseEntity<UsuarioResponse> obtenerUsuarioActual(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        
        UsuarioResponse response = new UsuarioResponse(
            usuario.getId(),
            usuario.getNombre(),
            usuario.getEmail(),
            usuario.getFechaRegistro()
        );
        
        return ResponseEntity.ok(response);
    }
}
