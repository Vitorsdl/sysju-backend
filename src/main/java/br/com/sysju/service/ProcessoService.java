package br.com.sysju.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import br.com.sysju.domain.Advogado;
import br.com.sysju.domain.Cliente;
import br.com.sysju.domain.Comarca;
import br.com.sysju.domain.Processo;
import br.com.sysju.repository.AdvogadoRepository;
import br.com.sysju.repository.ClienteRepository;
import br.com.sysju.repository.ComarcaRepository;
import br.com.sysju.repository.ProcessoRepository;

@Service
public class ProcessoService {
	
	@Autowired
	private ProcessoRepository rep;
	@Autowired
	private ClienteRepository cr;
	@Autowired
	private AdvogadoRepository ar;
	@Autowired
	private ComarcaRepository cor;
	
	public Iterable<Processo> getProcessos() {
		return rep.findAll();
	}
	
	public Optional<Processo> getProcessosById(Long id) {
		return rep.findById(id);
	}
	
	public Processo insert(Processo processo) {
		Assert.notNull(processo.getId(), "id null");
		Cliente cliente = cr.findById(processo.getCliente().getId()).orElseThrow(()-> 
		new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado"));
		processo.setCliente(cliente);
		
		Advogado advogado = ar.findById(processo.getAdvogado().getId()).orElseThrow(()->
		new ResponseStatusException(HttpStatus.BAD_REQUEST, "Advogado não encontrado"));
		processo.setAdvogado(advogado);
		
		Comarca comarca = cor.findById(processo.getComarca().getId()).orElseThrow(()->
		new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comarca não encontrada"));
		processo.setComarca(comarca);
		return rep.save(processo);
	}
	
	public Processo update(Processo processo, Long id) {
		Assert.notNull(processo.getId(), "id null");
		
		Optional<Processo> optional = getProcessosById(id);
		if(optional.isPresent()) {
			Processo db = optional.get();
			db.setNumero(processo.getNumero());
			db.setTipo(processo.getTipo());
			db.setProcedimento(processo.getProcedimento());
			db.setVara(processo.getVara());
			db.setResultadoRecurso(processo.getResultadoRecurso());
		
			return rep.save(db);
		}else {
			throw new RuntimeException("Falha ao atualizar.");
		}
		
	}
	
	public void delete(Long id) {
		Optional<Processo> processo = rep.findById(id); 
		if(processo.isPresent()) {
			rep.deleteById(id);
		}else {
			throw new RuntimeException("Falha ao excluir");
		}
	}

}
