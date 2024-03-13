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

import br.com.sysju.domain.Advogado;
import br.com.sysju.service.AdvogadoService;

@RestController
@RequestMapping("/advogados")
public class AdvogadoController {
	
	@Autowired
	private AdvogadoService as;
	
	@GetMapping()
	public ResponseEntity<Iterable<Advogado>> get() {
		return ResponseEntity.ok(as.getAdvogados());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id) {
		Optional<Advogado> advogado = as.getAdvogadosById(id);
		
		if(advogado.isPresent()) {
			return ResponseEntity.ok(as.getAdvogadosById(id));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void post(@RequestBody Advogado advogado) {
		Advogado c = as.insert(advogado);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String put(@PathVariable("id") Long id, @RequestBody Advogado advogado) {
		Advogado c = as.update(advogado, id);
		return "Alterado: " + c.getId();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String delete(@PathVariable("id") Long id) {
		as.delete(id);
		return "Deletado";
	}

}
