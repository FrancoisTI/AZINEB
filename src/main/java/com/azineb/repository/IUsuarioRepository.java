package com.azineb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azineb.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

	
	//metodo que busque usuario y clave
	// este metodo es como hacer un select
	public Usuario findByCorreoAndClave(String correo,String clave);
	
}
