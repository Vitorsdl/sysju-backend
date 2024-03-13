package br.com.sysju.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.sysju.domain.Cliente;
import br.com.sysju.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository rep;
	
	public Iterable<Cliente> getClientes() {
		return rep.findAll();
	}
	
	public Optional<Cliente> getClientesById(Long id) {
		return rep.findById(id);
	}
	
//	public List<Cliente> getClientesByNome(String nome) {
//		return rep.findByNome(nome);
//	}
	
	public Cliente insert(Cliente cliente) {
		Assert.notNull(cliente.getId(), "id null");
		return rep.save(cliente);
	}
	
	public Cliente update(Cliente cliente, Long id) {
		Assert.notNull(cliente.getId(), "id null");
		Optional<Cliente> optional = getClientesById(id);
		if(optional.isPresent()) {
			Cliente db = optional.get();
			db.setNome(cliente.getNome());
			db.setCpf(cliente.getCpf());
			db.setRg(cliente.getRg());
			db.setSexo(cliente.getSexo());
			db.setEmail(cliente.getEmail());
			db.setTelefone(cliente.getTelefone());
			db.setEndereco(cliente.getEndereco());
			db.setTipoPessoa(cliente.getTipoPessoa());
			
			return rep.save(db);
		}else {
			throw new RuntimeException("Falha ao atualizar");
		}
	}
	
	public void delete(Long id) {
		Optional<Cliente> cliente = getClientesById(id); 
		if(cliente.isPresent()) {
			rep.deleteById(id);
		}else {
			throw new RuntimeException("Falha ao excluir");
		}
	}

}
