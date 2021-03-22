package com.acc.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Contacorrente {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idContaCorrente;
    
    @Column
    private String contaCorrenteAgencia;
    
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String contaCorrenteNumero;
    
    @Column (nullable = false)
    private double contaCorrenteSaldo = 0.00;
    
    @ManyToOne
    @JoinColumn (name="cliente_idcliente")
    private Cliente idcliente;


    
    
    public long getIdContaCorrente() {
        return idContaCorrente;
    }

    public String getContaCorrenteAgencia() {
        return contaCorrenteAgencia;
    }

    public String getContaCorrenteNumero() {
        return contaCorrenteNumero;
    }
    
    public double getContaCorrenteSaldo() {
        return contaCorrenteSaldo;
    }
    
    public Cliente getIdCliente() {
        return idcliente;
    }
    public long getIdContaCorrente2() {
        return idContaCorrente;
    }
    public String getContaCorrenteNumero2() {
        return contaCorrenteNumero;
    }
    
    
    
    
    public void setIdContaCorrente(long idContaCorrente) {
        this.idContaCorrente = idContaCorrente;
    }
    
    public void setContaCorrenteAgencia(String contaCorrenteAgencia) {
        this.contaCorrenteAgencia = contaCorrenteAgencia;
    }
    
    public void setContaCorrenteNumero(String contaCorrenteNumero) {
        this.contaCorrenteNumero = contaCorrenteNumero;
    }

    public void setContaCorrenteSaldo(Double contaCorrenteSaldo) {
        this.contaCorrenteSaldo = contaCorrenteSaldo;
    }
    	
    public void setIdCliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }     
    
    public void setIdContaCorrente2(long idContaCorrente2) {
        this.idContaCorrente = idContaCorrente2;
    }

    public void setContaCorrenteNumero2(String	 contaCorrenteNumero2) {
        this.contaCorrenteNumero = contaCorrenteNumero2;
    }   
}
