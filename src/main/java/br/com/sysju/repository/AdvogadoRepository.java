package br.com.sysju.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.sysju.domain.Advogado;

@Repository
public interface AdvogadoRepository extends CrudRepository<Advogado, Long>{

}
