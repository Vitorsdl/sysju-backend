package br.com.sysju.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sysju.domain.Evento;
import br.com.sysju.service.EventoService;

@RestController
@RequestMapping("/eventos")
public class EventosController {
	@Autowired
	private EventoService es;
	
	@GetMapping()
	public ResponseEntity<Iterable<Evento>> get() {
		return ResponseEntity.ok(es.getEventos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id) {
		Optional<Evento> evento = es.getEventosById(id);
		
		if(evento.isPresent()) {
		return ResponseEntity.ok(es.getEventosById(id));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void post(@RequestBody Evento evento) {
		Evento e = es.insert(evento);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String put(@PathVariable("id") Long id, @RequestBody Evento evento) {
		Evento e = es.update(evento, id);
		return "Alterado: " + e.getId();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String delete(@PathVariable("id") Long id) {
		es.delete(id);
		return "Deletado.";
	}

}
