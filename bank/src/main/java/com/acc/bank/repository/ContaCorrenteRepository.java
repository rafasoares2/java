package com.acc.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository; 

import com.acc.bank.entity.Contacorrente;

@EnableJpaRepositories
@Repository
public interface ContaCorrenteRepository extends JpaRepository<Contacorrente, Long> {
	
	//Add para pesquisar um campo que é string, add essa exceção.
	Optional<Contacorrente> findByContaCorrenteNumero(String contaorrentenumero);

	
}


