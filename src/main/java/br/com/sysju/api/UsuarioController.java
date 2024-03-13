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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sysju.domain.Usuario;
import br.com.sysju.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService us;
	
	@GetMapping()
	public ResponseEntity<Iterable<Usuario>> get() {
		return ResponseEntity.ok(us.getUsuario());
	}
	
	@GetMapping("/{login}")
	public ResponseEntity get(@PathVariable("login") String login) {
		Optional<Usuario> usuario = us.getUsuarioByLogin(login);
		
		if(usuario.isPresent()) {
		return ResponseEntity.ok(us.getUsuarioByLogin(login));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void post(@RequestBody Usuario usuario) {
		Usuario u = us.insert(usuario);

	}
	
	@PutMapping("/{login}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String put(@PathVariable("login") String login, @RequestBody Usuario usuario) {
		Usuario u = us.update(usuario, login);
		return "Alterado: " + u.getLogin();
	}
	
	@DeleteMapping("/{login}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String delete(@PathVariable("login") String login) {
		us.delete(login);
		return "Deletado";
	}
}
