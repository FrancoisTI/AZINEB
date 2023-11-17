package com.azineb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "usuario" )

public class Usuario {

	@Id
	private int codigo_usuario;
	private String nombre_usuario;
	private String apellido_usuario;
	@Column(name="user_usuario")
	private String correo;
	@Column(name="password_usuario")
	private String clave;
	private int id_tipo;
	
}
