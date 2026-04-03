package com.guilherme.estoque.service;

import org.springframework.stereotype.Service;

import com.guilherme.estoque.model.Produto;
import com.guilherme.estoque.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private final ProdutoRepository produtoRepository;

	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	public Produto cadastrarProduto(Produto produto) {
		if (produtoRepository.findByCodigo(produto.getCodigo()).isPresent()) {
			throw new IllegalArgumentException("Produto já existente.");
		}
		return produtoRepository.save(produto);
	}

	public void removerProduto(String codigo) {
		Produto codigoProduto = buscarProduto(codigo);
		produtoRepository.delete(codigoProduto);
	}

	public Produto buscarProduto(String codigo) {
		return produtoRepository.findByCodigo(codigo)
				.orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));
	}

	public Produto atualizarEndereco(String codigo, String enderecoNovo) {
		Produto produto = buscarProduto(codigo);
		produto.setEndereco(enderecoNovo);
		return produtoRepository.save(produto);
	}

}

