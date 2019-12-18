package com.eiturre.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eiturre.minhasfinancas.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
