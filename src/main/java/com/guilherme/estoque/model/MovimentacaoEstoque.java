package com.guilherme.estoque.model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;

@Entity
public class MovimentacaoEstoque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoMovimentacao tipoMovimentacao;

	@Column(nullable = false)
	private Integer quantidade;

	@Column(nullable = false)
	private LocalDateTime data;

	@ManyToOne(optional = false)
	private Produto produto;

	// Construtor padrão
	public MovimentacaoEstoque() {
	}

	// Construtor sem id
	public MovimentacaoEstoque(TipoMovimentacao tipoMovimentacao, Integer quantidade, LocalDateTime data,
			Produto produto) {
		this.tipoMovimentacao = tipoMovimentacao;
		this.quantidade = quantidade;
		this.data = data;
		this.produto = produto;
	}

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}