package com.flexreact.repository;

import com.flexreact.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, UUID> {
    
    List<Producto> findByCategoria(String categoria);
    
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}
