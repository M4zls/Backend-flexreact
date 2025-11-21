package com.flexreact.service;

import com.flexreact.entity.Producto;
import com.flexreact.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Producto obtenerPorId(UUID id) {
        return productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Transactional(readOnly = true)
    public List<Producto> obtenerPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    @Transactional
    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }

    @Transactional
    public Producto actualizar(UUID id, Producto producto) {
        Producto existente = obtenerPorId(id);

        existente.setNombre(producto.getNombre());
        existente.setPrecio(producto.getPrecio());
        existente.setStock(producto.getStock());
        existente.setCategoria(producto.getCategoria());
        existente.setImagen(producto.getImagen());
        existente.setDescuento(producto.getDescuento());

        return productoRepository.save(existente);
    }

    @Transactional
    public void eliminar(UUID id) {
        productoRepository.deleteById(id);
    }
}
