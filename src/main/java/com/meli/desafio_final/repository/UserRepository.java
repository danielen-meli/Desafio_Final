package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, String>{

	Usuario findByUser(String user);
	
}
