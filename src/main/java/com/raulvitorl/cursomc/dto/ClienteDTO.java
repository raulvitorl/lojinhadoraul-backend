package com.raulvitorl.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.raulvitorl.cursomc.domain.Cliente;
import com.raulvitorl.cursomc.services.validaton.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable{
static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Preenchimento Obrigatório!")
	@Length(min=5,max=120,message ="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	
	@NotEmpty(message = "Preenchimento Obrigatório!")
	@Email(message = "Email inválido!")
	private String email;
	public ClienteDTO(Integer id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	public ClienteDTO() {
		super();
	}
	
	public ClienteDTO(Cliente obj) {
		this.email = obj.getEmail();
		this.nome = obj.getNome();
		this.id = obj.getId();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}