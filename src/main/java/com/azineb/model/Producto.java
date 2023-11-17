package com.azineb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "producto")
@Data
public class Producto {
	@Id
	private int id_producto;
	private String nombre_producto;
	private int stock_producto;
	private double precio_producto;
	private int id_estado;
	private int id_categoria;
	private int id_proveedor;

	@ManyToOne
	@JoinColumn(name = "id_categoria", updatable = false, insertable = false)
	Categoria objCategoria;

	@ManyToOne
	@JoinColumn(name = "id_proveedor", updatable = false, insertable = false)
	Proveedor objProveedor;
	
	@ManyToOne
	@JoinColumn(name = "id_estado", updatable = false, insertable = false)
	Estado objEstado;
}
