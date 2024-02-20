package br.com.unicesumar.modulo12.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "clientes")
@Entity(name = "Cliente")
public class Cliente {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Size(max = 150, message = "O nome fantasia do fornecedor não deve conter mais de 150 caracteres")
	@NotBlank(message = "O nome fantasia do fornecedor é obrigatório")
	@Column(name = "nome_completo")
	private String nomeCompleto;
	
	@Size(max = 30, message = "O documento é obrigatório")
	@Column(name = "email")
	private String documento;
	
	@Size(max = 100, message = "O e-mail é obrigatório")
	@Column(name = "email")
	private String email;

}
