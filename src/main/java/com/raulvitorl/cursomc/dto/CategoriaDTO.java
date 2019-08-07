package com.raulvitorl.cursomc.dto;

import java.io.Serializable;

import com.raulvitorl.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable{
private static final long serialVersionUID = 1L;


	private Integer id;
	private String nome;
	
	public CategoriaDTO(){
		
	}
	
	public CategoriaDTO(Categoria obj){
		this.id = obj.getId();
		this.nome = obj.getNome();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

}
