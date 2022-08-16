package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Na autenticação o usuário será encontrato pelo seu nome "user"
 */
@Repository
public interface UserRepository extends JpaRepository<Usuario, String>{

	Usuario findByUser(String user);
	
}
