package com.guilherme.estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.estoque.model.Produto;
import com.guilherme.estoque.service.ProdutoService;

@RequestMapping("/produtos")
@RestController
public class ProdutoController {

	private final ProdutoService produtoService;
	

	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
	@PostMapping("/")
	public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto){
		return ResponseEntity.ok().body(produtoService.cadastrarProduto(produto));
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> removerProduto(@PathVariable String codigo){
		produtoService.removerProduto(codigo);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Produto> buscarProduto(@PathVariable String codigo){
		return ResponseEntity.ok().body(produtoService.buscarProduto(codigo));
	}
	
	@PatchMapping("/{codigo}/endereco")
	public ResponseEntity<Produto> atualizarEndereco(@PathVariable String codigo, @RequestBody String enderecoNovo){
		return ResponseEntity.ok(produtoService.atualizarEndereco(codigo, enderecoNovo));
	}
}


