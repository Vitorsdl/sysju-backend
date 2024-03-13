package br.com.sysju.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.sysju.domain.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String>{

	Optional<Usuario> findByLogin(String login);	
	
}
