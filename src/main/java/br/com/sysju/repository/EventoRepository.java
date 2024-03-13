package br.com.sysju.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.sysju.domain.Evento;

@Repository
public interface EventoRepository extends CrudRepository<Evento, Long>{

}
