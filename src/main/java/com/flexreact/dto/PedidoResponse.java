package com.flexreact.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponse {
    private UUID id;
    private String estado;
    private BigDecimal total;
    private UUID usuarioId;
    private String usuarioNombre;
    private String usuarioEmail;
    private String direccionEnvio;
    private String metodoPago;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ItemPedidoResponse> items;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemPedidoResponse {
        private UUID id;
        private UUID productoId;
        private String productoNombre;
        private Integer cantidad;
        private BigDecimal precioUnitario;
        private BigDecimal subtotal;
    }
}
