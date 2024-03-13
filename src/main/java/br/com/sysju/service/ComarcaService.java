package br.com.sysju.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sysju.domain.Comarca;
import br.com.sysju.repository.ComarcaRepository;

@Service
public class ComarcaService {
	
	@Autowired
	private ComarcaRepository co;
	
	public Iterable<Comarca> getComarcas() {
		return co.findAll();
	}

	public Optional<Comarca> getComarcasById(Long id) {
		return co.findById(id);
	}

}
