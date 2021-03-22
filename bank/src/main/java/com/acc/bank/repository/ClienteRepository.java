package com.acc.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.acc.bank.entity.Cliente;

@EnableJpaRepositories
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> { 
	
	Optional<Cliente> findByClienteCpf(String clienteCpf);

}
