package com.flexreact.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class CrearPedidoRequest {
    
    private String email;
    private String nombre;
    
    @NotEmpty(message = "El pedido debe tener al menos un producto")
    private List<ItemPedidoRequest> items;
    
    @NotNull(message = "El total es requerido")
    private BigDecimal total;
    
    private String direccionEnvio;
    private String metodoPago;
    
    @Data
    public static class ItemPedidoRequest {
        @NotNull(message = "El ID del producto es requerido")
        private UUID productoId;
        
        @NotNull(message = "La cantidad es requerida")
        private Integer cantidad;
        
        @NotNull(message = "El precio unitario es requerido")
        private BigDecimal precioUnitario;
    }
}
