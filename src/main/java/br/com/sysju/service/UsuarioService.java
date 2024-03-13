package br.com.sysju.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.sysju.domain.Usuario;
import br.com.sysju.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository ur;

	public Iterable<Usuario> getUsuario() {
		return ur.findAll();
	}
	
	public Optional<Usuario> getUsuarioByLogin(String login) {
		return ur.findByLogin(login);
	}

	public Usuario insert(Usuario usuario) {
		Assert.notNull(usuario.getLogin(), "usuario null");
		return ur.save(usuario);
	}

	public Usuario update(Usuario usuario, String login) {
		Assert.notNull(usuario.getLogin(), "usuario null");
		Optional<Usuario> optional = getUsuarioByLogin(login);
		if(optional.isPresent()) {
			Usuario db = optional.get();
			db.setLogin(usuario.getLogin());
			db.setSenha(usuario.getSenha());
			db.setRoles(usuario.getRoles());

			return ur.save(db);
		}else {
			throw new RuntimeException("Falha ao atualizar");
		}
	}

	public void delete(String login) {
		Optional<Usuario> usuario = getUsuarioByLogin(login); 
		if(usuario.isPresent()) {
			ur.deleteById(login);
		}else {
			throw new RuntimeException("Falha ao excluir");
		}
	}
}
