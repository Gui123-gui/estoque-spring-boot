package com.guilherme.estoque.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.guilherme.estoque.enums.TipoMovimentacao;
import com.guilherme.estoque.model.MovimentacaoEstoque;
import com.guilherme.estoque.model.Produto;
import com.guilherme.estoque.repository.MovimentacaoRepository;
import com.guilherme.estoque.repository.ProdutoRepository;

@Service
public class MovimentacaoService {

	private final MovimentacaoRepository movimentacaoRepository;
	private ProdutoRepository produtoRepository;

	public Produto findByCodigo(String codigo){
		return produtoRepository.findByCodigo(codigo)
				.orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));

	}

	public MovimentacaoService(MovimentacaoRepository movimentacaoReporitory, ProdutoRepository produtoRepository) {
		this.movimentacaoRepository = movimentacaoReporitory;
		this.produtoRepository = produtoRepository;
	}

	public MovimentacaoEstoque saidaMovimentacao(String codigo, Integer quantidade) {
		Produto produto = findByCodigo(codigo);
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
		return movimentacaoRepository.save(movimentacao);

	}

	public MovimentacaoEstoque entradaMovimentacao(String codigo, Integer quantidade) {
		Produto produto = findByCodigo(codigo);

		Integer quantidadeProduto = produto.getQuantidade();
		quantidadeProduto += quantidade;
		produto.setQuantidade(quantidadeProduto);
		produtoRepository.save(produto);

		MovimentacaoEstoque movimentacao = new MovimentacaoEstoque(TipoMovimentacao.ENTRADA, quantidade,
				LocalDateTime.now(), produto);
		return movimentacaoRepository.save(movimentacao);

	}
	
	public List<MovimentacaoEstoque> historicoMovimentacao(String codigo) {
		Produto produto = findByCodigo(codigo);
		List<MovimentacaoEstoque> mov = movimentacaoRepository.findByProdutoId(produto.getId());
		return mov;
	}

}



