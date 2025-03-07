package com.demo.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.demo.demo.model.Categoria;

@Repository
public class CategoriaRepository {
    private final List<Categoria> categorias = new ArrayList<>();
    private Long nextId = 1L;

    public Categoria add(Categoria categoria) {
        categoria.setId(nextId++);
        categorias.add(categoria);
        return categoria;
    }

    public Optional<Categoria> findById(Long id) {
        return categorias.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public List<Categoria> findAll() {
        return categorias;
    }

    public Categoria update(Categoria categoria) {
        return categorias.stream()
            .filter(c -> c.getId().equals(categoria.getId()))
            .findFirst()
            .map(
                c -> {
                    c.setNome(categoria.getNome());
                    c.setDescricao(categoria.getDescricao());
                    return c;
                }
            )
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada para atualização"));
    }

    public void delete(Long id) {
        categorias.removeIf(c -> c.getId().equals(id));
    }
    
}
