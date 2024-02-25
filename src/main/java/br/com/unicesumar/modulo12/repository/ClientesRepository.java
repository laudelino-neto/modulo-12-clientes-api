package br.com.unicesumar.modulo12.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.unicesumar.modulo12.entity.Cliente;

@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Integer>{

	@Query(value = 
			"SELECT c "
			+ "FROM Cliente c "
			+ "WHERE Upper(c.nomeCompleto) LIKE Upper(:nome)",
			countQuery = 
					"SELECT Count(c) "
					+ "FROM Cliente c "
					+ "WHERE Upper(c.nomeCompleto) LIKE Upper(:nome)")
	public Page<Cliente> listarPor(String nome, Pageable paginacao);
	
	@Query(value = 
			"SELECT c "
			+ "FROM Cliente c "
			+ "WHERE c.id = :id ")
	public Cliente buscarPor(Integer id);
	
}
