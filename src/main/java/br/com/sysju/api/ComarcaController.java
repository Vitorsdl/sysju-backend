package br.com.sysju.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sysju.domain.Comarca;
import br.com.sysju.service.ComarcaService;

@RestController
@RequestMapping("/comarcas")
public class ComarcaController {
	
	@Autowired
	private ComarcaService cos;
	
	@GetMapping()
	public Iterable<Comarca> get() {
		return cos.getComarcas();
	}
	
	@GetMapping("/{id}")
	public Optional<Comarca> get(@PathVariable("id") Long id) {
		return cos.getComarcasById(id);
	}

}
