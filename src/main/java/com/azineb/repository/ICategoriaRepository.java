package com.azineb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azineb.model.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Integer>{

}
