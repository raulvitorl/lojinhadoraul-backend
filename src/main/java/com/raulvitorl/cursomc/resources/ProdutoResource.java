package com.raulvitorl.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raulvitorl.cursomc.domain.Produto;
import com.raulvitorl.cursomc.dto.ProdutoDTO;
import com.raulvitorl.cursomc.resources.utils.URL;
import com.raulvitorl.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id){
		Produto obj = service.find(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
		@RequestParam(name = "nome", defaultValue = "") String nome,
		@RequestParam(name = "categorias", defaultValue = "") String categorias,
		@RequestParam(name = "page", defaultValue = "0") Integer page, 
		@RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
		@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy, 
		@RequestParam(name = "direction", defaultValue = "ASC") String direction){
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeList(categorias);
		Page<Produto> lista = service.search(nomeDecoded,ids,page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = lista.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDto);		
	}
	
	
	
}
