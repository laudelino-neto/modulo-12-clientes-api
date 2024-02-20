package br.com.unicesumar.modulo12.service;

import org.postgresql.shaded.com.ongres.scram.common.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.unicesumar.modulo12.entity.Cliente;
import br.com.unicesumar.modulo12.repository.ClientesRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Service
public class ClienteService {

	@Autowired
	private ClientesRepository repository;
	
	public Cliente salvar(Cliente cliente) {
		Cliente clienteSalvo = repository.save(cliente); 
		return clienteSalvo;
	}
	
	public void removerPor(
			@NotNull(message = "O id é obrigatório")
			Integer id) {
		Cliente clienteSalvo = repository.buscarPor(id);
		Preconditions.checkNotNull(clienteSalvo, 
				"Não existe cliente vinculado ao id informado");
		this.repository.delete(clienteSalvo);			
	}
	
	public Cliente buscarPor(Integer id) {
		Cliente clienteSalvo = repository.buscarPor(id);
		Preconditions.checkNotNull(clienteSalvo, 
				"Não existe cliente vinculado ao id informado");
		return clienteSalvo;
	}
	
	public Page<Cliente> listarPor(
			@NotBlank(message = "O nome é obrigatório")
			String nome,
			Pageable paginacao){			
		
		nome = "%" + nome + "%";
		
		return repository.listarPor(nome, paginacao); 
		
	}
	
}
