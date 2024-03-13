package br.com.sysju.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.sysju.domain.Evento;
import br.com.sysju.repository.EventoRepository;

@Service
public class EventoService {
	
	@Autowired
	private EventoRepository rep;
	
	public Iterable<Evento> getEventos() {
		return rep.findAll();		
	}
	
	public Optional<Evento> getEventosById(Long id) {
		return rep.findById(id);
	}
	
	public Evento insert(Evento evento) {
		Assert.notNull(evento.getId(), "id null");
		return rep.save(evento);
	}
	
	public Evento update(Evento evento, Long id) {
		Assert.notNull(evento.getId(), "id null");
		Optional<Evento> optional = getEventosById(id);
		if(optional.isPresent()) {
			Evento db = optional.get();
			db.setStatus(evento.getStatus());
			db.setDescricao(evento.getDescricao());
			db.setTipo(evento.getTipo());
			db.setDataHora(evento.getDataHora());
			
			return rep.save(db);
		}else {
			throw new RuntimeException("Falha ao atualizar");
		}
		
	}
	
	public void delete(Long id) {
		Optional<Evento> evento = getEventosById(id); 
		if(evento.isPresent()) {
			rep.deleteById(id);
		}else {
			throw new RuntimeException("Falha ao excluir");
		}
	}

}
