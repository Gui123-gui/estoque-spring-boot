package com.guilherme.estoque.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.guilherme.estoque.model.MovimentacaoEstoque;
import com.guilherme.estoque.model.Produto;
import com.guilherme.estoque.model.TipoMovimentacao;
import com.guilherme.estoque.repository.MovimentacaoRepository;
import com.guilherme.estoque.repository.ProdutoRepository;

@Service
public class MovimentacaoService {

	private final MovimentacaoRepository movimentacaoReporitory;
	private ProdutoRepository produtoRepository;

	public MovimentacaoService(MovimentacaoRepository movimentacaoReporitory, ProdutoRepository produtoRepository) {
		this.movimentacaoReporitory = movimentacaoReporitory;
		this.produtoRepository = produtoRepository;
	}

	public MovimentacaoEstoque saidaMovimentacao(String codigo, Integer quantidade) {
		Produto produto = produtoRepository.findByCodigo(codigo)
				.orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));
		if (produto.getQuantidade() < quantidade) {
			throw new IllegalArgumentException("Quantidade insuficiente");
		} else {
			Integer quantidadeProduto = produto.getQuantidade();
			quantidadeProduto -= quantidade;
			produto.setQuantidade(quantidadeProduto);
			produtoRepository.save(produto);
		}
		MovimentacaoEstoque movimentacao = new MovimentacaoEstoque(TipoMovimentacao.SAIDA, quantidade,
				LocalDateTime.now(), produto);
		return movimentacaoReporitory.save(movimentacao);

	}

	public MovimentacaoEstoque entradaMovimentacao(String codigo, Integer quantidade) {
		Produto produto = produtoRepository.findByCodigo(codigo)
				.orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));

		Integer quantidadeProduto = produto.getQuantidade();
		quantidadeProduto += quantidade;
		produto.setQuantidade(quantidadeProduto);
		produtoRepository.save(produto);

		MovimentacaoEstoque movimentacao = new MovimentacaoEstoque(TipoMovimentacao.ENTRADA, quantidade,
				LocalDateTime.now(), produto);
		return movimentacaoReporitory.save(movimentacao);

	}
	
	public List<MovimentacaoEstoque> historicoMovimentacao(String codigo) {
		Produto produto = produtoRepository.findByCodigo(codigo)
				.orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));
		List<MovimentacaoEstoque> mov = movimentacaoReporitory.findByProdutoId(produto.getId());
		return mov;
	}

}
