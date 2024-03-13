package br.com.sysju.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.sysju.domain.Processo;

@Repository
public interface ProcessoRepository extends CrudRepository<Processo, Long> {

}
