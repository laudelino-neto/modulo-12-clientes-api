package br.com.unicesumar.modulo12.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.base.Preconditions;

import br.com.unicesumar.modulo12.entity.Cliente;
import br.com.unicesumar.modulo12.repository.ClientesRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class ClienteService {

	@Autowired
	private ClientesRepository repository;
	
	public Cliente salvar(
			@Valid
			Cliente cliente) {
		Cliente clienteSalvo = repository.save(cliente); 
		return clienteSalvo;
	}
	
	public void removerPor(
			@NotNull(message = "O id é obrigatório")
			@Positive(message = "O id deve ser positivo")
			Integer id) {
		Cliente clienteSalvo = repository.buscarPor(id);
		Preconditions.checkNotNull(clienteSalvo, 
				"Não existe cliente vinculado ao id informado");
		this.repository.delete(clienteSalvo);			
	}
	
	public Cliente buscarPor(
			@NotNull(message = "O id é obrigatório")
			@Positive(message = "O id deve ser positivo")
			Integer id) {
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
