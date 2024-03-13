package br.com.sysju.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.sysju.domain.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	
//	@Query("FROM Cliente WHERE UPPER(nome) LIKE CONCAT('%', UPPER(?1), '%')")
//	public List<Cliente> findByNome(String nome);

}
