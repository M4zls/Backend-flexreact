package com.flexreact.service;

import com.flexreact.dto.*;
import com.flexreact.entity.Usuario;
import com.flexreact.repository.UsuarioRepository;
import com.flexreact.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    
    @Transactional
    public AuthResponse registrar(RegisterRequest request) {
        // Validar que las contraseñas coincidan
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Las contraseñas no coinciden");
        }
        
        // Validar que el email no exista
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        
        // Crear usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        
        usuario = usuarioRepository.save(usuario);
        
        // Generar token
        String token = jwtUtil.generateToken(usuario.getId(), usuario.getEmail());
        
        // Crear response
        UsuarioResponse usuarioResponse = new UsuarioResponse(
            usuario.getId(),
            usuario.getNombre(),
            usuario.getEmail(),
            usuario.getFechaRegistro()
        );
        
        return new AuthResponse(token, usuarioResponse);
    }
    
    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        // Buscar usuario por email
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));
        
        // Validar contraseña
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }
        
        // Generar token
        String token = jwtUtil.generateToken(usuario.getId(), usuario.getEmail());
        
        // Crear response
        UsuarioResponse usuarioResponse = new UsuarioResponse(
            usuario.getId(),
            usuario.getNombre(),
            usuario.getEmail(),
            usuario.getFechaRegistro()
        );
        
        return new AuthResponse(token, usuarioResponse);
    }
}
