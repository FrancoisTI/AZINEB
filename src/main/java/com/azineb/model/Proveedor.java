package com.azineb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "proveedor")
@Data
public class Proveedor {
	@Id
	private int id_proveedor;
	private String nombre_proveedor;
}
