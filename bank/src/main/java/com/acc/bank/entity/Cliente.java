package com.acc.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idcliente;
    
    @Column(nullable = false)
    private String clientenome;
    
    
    @Column(nullable = false)
    private String clienteCpf;
    
    @Column(nullable = false)
    private String clientefone;
    

    
    
    
    public long getIdCliente() {
        return idcliente;
    }

    public String getClienteNome() {
        return clientenome;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }
    
    public String getClienteFone() {
        return clientefone;
    }
    
    
    
    
    public void setIdCliente(long idcliente) {
        this.idcliente = idcliente;
    }
    
    public void setClienteNome(String clientenome) {
        this.clientenome = clientenome;
    }
    
    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    public void setClienteFone(String clientefone) {
        this.clientefone = clientefone;
    }
    	

}
