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

import br.com.sysju.domain.Processo;
import br.com.sysju.service.ProcessoService;

@RestController
@RequestMapping("/processos")
public class ProcessosController {
	@Autowired
	private ProcessoService ps;
	
	@GetMapping()
	public ResponseEntity<Iterable<Processo>> get() {
		
		return ResponseEntity.ok(ps.getProcessos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id) {
		Optional<Processo> processo = ps.getProcessosById(id);
		
		if(processo.isPresent()) {
		return ResponseEntity.of(ps.getProcessosById(id));
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void post(@RequestBody Processo processo) {
		Processo p = ps.insert(processo);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String put(@PathVariable("id") Long id, @RequestBody Processo processo) {
		Processo p = ps.update(processo, id);
		return "Alterado: " + p.getId();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String delete(@PathVariable("id") Long id) {
		ps.delete(id);
		return "Deletado.";
	}

}
