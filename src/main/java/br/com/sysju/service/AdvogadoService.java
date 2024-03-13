package br.com.sysju.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.sysju.domain.Advogado;
import br.com.sysju.repository.AdvogadoRepository;

@Service
public class AdvogadoService {
	
	@Autowired
	private AdvogadoRepository ar;
	
	public Iterable<Advogado> getAdvogados() {
		return ar.findAll();
	}
	
	public Optional<Advogado> getAdvogadosById(Long id) {
		return ar.findById(id);
	}
	
	public Advogado insert(Advogado advogado) {
		Assert.notNull(advogado.getId(), "id null");
		return ar.save(advogado);
	}
	
	public Advogado update(Advogado advogado, Long id) {
		Assert.notNull(advogado.getId(), "id null");
		Optional<Advogado> optional = getAdvogadosById(id);
		if(optional.isPresent()) {
			Advogado db = optional.get();
			db.setNome(advogado.getNome());
			return ar.save(db);
		}else {
			throw new RuntimeException("Falha ao atualizar");
		}
	}
	
	public void delete(Long id) {
		Optional<Advogado> advogado = getAdvogadosById(id); 
		if(advogado.isPresent()) {
			ar.deleteById(id);
		}else {
			throw new RuntimeException("Falha ao excluir");
		}
	}

}
