package com.guilherme.estoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guilherme.estoque.model.MovimentacaoEstoque;

public interface MovimentacaoRepository extends JpaRepository<MovimentacaoEstoque, Long>{
	
	List<MovimentacaoEstoque> findByProdutoId(Long produtoId);

}



