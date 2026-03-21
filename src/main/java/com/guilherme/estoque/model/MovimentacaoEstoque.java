package com.guilherme.estoque.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_movimentacao")
public class MovimentacaoEstoque {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private int quantidade;
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    // Construtores
    public MovimentacaoEstoque() {
    }

    public MovimentacaoEstoque(Long id, String tipo, int quantidade, LocalDateTime data, Produto produto) {
        this.id = id;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.data = data;
        this.produto = produto;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}