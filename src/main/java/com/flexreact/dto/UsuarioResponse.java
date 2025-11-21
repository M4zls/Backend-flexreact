package com.flexreact.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {
    private UUID id;
    private String nombre;
    private String email;
    private LocalDateTime fechaRegistro;
}
