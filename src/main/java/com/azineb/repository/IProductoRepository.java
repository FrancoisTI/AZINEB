package com.azineb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azineb.model.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Integer> {

}
