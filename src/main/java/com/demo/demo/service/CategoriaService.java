package com.demo.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.demo.demo.dto.CategoriaDTO;
import com.demo.demo.model.Categoria;
import com.demo.demo.repository.CategoriaRepository;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaDTO add(CategoriaDTO categoria) {
        Categoria newCategory = new Categoria(categoria.getId(), categoria.getNome(), categoria.getDescricao());
        Categoria savedCategory = categoriaRepository.add(newCategory);
        return new CategoriaDTO(savedCategory.getId(), savedCategory.getNome(), savedCategory.getDescricao());
    }

    public CategoriaDTO findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return new CategoriaDTO(categoria.getId(), categoria.getNome(), categoria.getDescricao());
    }

    public List<CategoriaDTO> findAll() {   
        return categoriaRepository.findAll().stream()
            .map(c -> new CategoriaDTO(c.getId(), c.getNome(), c.getDescricao()))
            .collect(Collectors.toList());
    }

    public CategoriaDTO update(Long id, CategoriaDTO categoria) {
        Categoria updatedCategory = categoriaRepository.update(new Categoria(id, categoria.getNome(), categoria.getDescricao()));
        return new CategoriaDTO(updatedCategory.getId(), updatedCategory.getNome(), updatedCategory.getDescricao());
    }

    public void delete(Long id) {
        categoriaRepository.delete(id);
    }
}
