package br.com.unicesumar.modulo12.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import br.com.unicesumar.modulo12.controller.converter.MapConverter;
import br.com.unicesumar.modulo12.entity.Cliente;
import br.com.unicesumar.modulo12.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService service;
	
	@Autowired
	private MapConverter converter;
	
	public ResponseEntity<?> inserir(
			@RequestBody
			Cliente cliente){
		Preconditions.checkArgument(cliente.getId() == null, 
				"O cliente não deve possuir ID na inserção.");
		Cliente clienteSalvo = service.salvar(cliente); 
		return ResponseEntity.created(URI.create("/clientes/id/" 
				+ clienteSalvo.getId())).build();
	}
	
	public ResponseEntity<?> alterar(
			@RequestBody
			Cliente cliente){
		Preconditions.checkArgument(cliente.getId() != null, 
				"O id do cliente é obrigatório");
		Cliente clienteAtualizado = service.salvar(cliente); 
		return ResponseEntity.ok(converter.toJsonMap(clienteAtualizado));
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPor(@PathVariable("id") Integer id) {
		Cliente clienteEncontrado = service.buscarPor(id);
		return ResponseEntity.ok(converter.toJsonMap(clienteEncontrado)); 
	}
	
	@GetMapping
	public ResponseEntity<?> listarPor(
			@RequestParam("nome")
			String nome, 
			@RequestParam("pagina")
			Optional<Integer> pagina) {
		
		Pageable paginacao = null;

		if (pagina.isPresent()) {
			paginacao = PageRequest.of(pagina.get(), 15);
		} else {
			paginacao = PageRequest.of(0, 15);
		}

		Page<Cliente> clientes = service.listarPor(nome, paginacao);

		return ResponseEntity.ok(converter.toJsonList(clientes));		

	}	
	
}
