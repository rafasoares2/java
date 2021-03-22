package com.acc.bank.controller;

import java.lang.annotation.Target;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.acc.bank.entity.Contacorrente;
import com.acc.bank.constantes.Mensagem;
import com.acc.bank.repository.ContaCorrenteRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
@Api(value="Bank - Sacar - Depositar - Transferir")
@CrossOrigin(origins="*")
public class TransacoesController {
    @Autowired
    private ContaCorrenteRepository _transacoesRepository;
       

    //Depositar pelo id da conta
    @RequestMapping(value = "/depositar/{idcontacorrente}", method =  RequestMethod.PUT)
    @ApiOperation(value="Depositar pelo id da conta")
    public ResponseEntity<Contacorrente> Depositar(@PathVariable(value = "idcontacorrente") long idcontacorrente, @Valid @RequestBody Contacorrente newContaCorrente)
    {
        Optional<Contacorrente> oldContaCorrente = _transacoesRepository.findById(idcontacorrente);
        if(oldContaCorrente.isPresent()){
        	Contacorrente contacorrente = oldContaCorrente.get();
            
             if(newContaCorrente.getContaCorrenteSaldo() >0 )
             { 
            	 double saldo = newContaCorrente.getContaCorrenteSaldo() + contacorrente.getContaCorrenteSaldo();
            	 contacorrente.setContaCorrenteSaldo(saldo);
            	 _transacoesRepository.save(contacorrente);
            	 
              	 return new ResponseEntity(Mensagem.TRSCSUCESSO.getDescricao(), HttpStatus.OK);
             }
             else
                 return new ResponseEntity(Mensagem.VNEGATIVO.getDescricao(),HttpStatus.NOT_FOUND);            	
        }
        else
            return new ResponseEntity(Mensagem.DADOSN.getDescricao(),HttpStatus.NOT_FOUND);
    }
   
    
    
    
    //Depositar pelo numero da conta
    @RequestMapping(value = "/depositar/{contaorrentenumero}", method =  RequestMethod.PUT)
    @ApiOperation(value="Depositar pelo id da conta")
    public ResponseEntity<Contacorrente> DepositarN(@PathVariable(value = "contaorrentenumero") String contaorrentenumero, @Valid @RequestBody Contacorrente newContaCorrente)
    {
        Optional<Contacorrente> oldContaCorrente = _transacoesRepository.findByContaCorrenteNumero(contaorrentenumero);
        if(oldContaCorrente.isPresent()){
        	Contacorrente contacorrente = oldContaCorrente.get();
            
             if(newContaCorrente.getContaCorrenteSaldo() >0 )
             { 
            	 double saldo = newContaCorrente.getContaCorrenteSaldo() + contacorrente.getContaCorrenteSaldo();
            	 contacorrente.setContaCorrenteSaldo(saldo);
            	 _transacoesRepository.save(contacorrente);
            	 
              	 return new ResponseEntity(Mensagem.TRSCSUCESSO.getDescricao(), HttpStatus.OK);
             }
             else
                 return new ResponseEntity(Mensagem.VNEGATIVO.getDescricao(),HttpStatus.NOT_FOUND);            	
        }
        else
            return new ResponseEntity(Mensagem.DADOSN.getDescricao(),HttpStatus.NOT_FOUND);
    }
    
    
    
    
    //Sacar pelo id da conta
    @RequestMapping(value = "/sacar/{idcontacorrente}", method =  RequestMethod.PUT)
    @ApiOperation(value="Sacar pelo id da conta")
    public ResponseEntity<Contacorrente> Sacar(@PathVariable(value = "idcontacorrente") long idcontacorrente, @RequestParam (value = "valor") double valor)
    {
        Optional<Contacorrente> oldContaCorrente = _transacoesRepository.findById(idcontacorrente);
        if(oldContaCorrente.isPresent()){
            Contacorrente contacorrente = oldContaCorrente.get();
            
            if (valor >0 && valor <= contacorrente.getContaCorrenteSaldo() && contacorrente.getContaCorrenteSaldo() >= 0) 
            {	
            	double saldo = contacorrente.getContaCorrenteSaldo() - valor;
            	contacorrente.setContaCorrenteSaldo(saldo);
            	_transacoesRepository.save(contacorrente);
            	
              	 return new ResponseEntity(Mensagem.TRSCSUCESSO.getDescricao(), HttpStatus.OK);
            }
            else
           	 return new ResponseEntity(Mensagem.SEMSALDO.getDescricao(), HttpStatus.NOT_ACCEPTABLE);
            }
        else
            return new ResponseEntity(Mensagem.DADOSN.getDescricao(),HttpStatus.NOT_FOUND);
        }

    
    //Sacar pelo id da conta
    @RequestMapping(value = "/sacar/{contaorrentenumero}", method =  RequestMethod.PUT)
    @ApiOperation(value="Sacar pelo numero da conta")
    public ResponseEntity<Contacorrente> SacarN(@PathVariable(value = "contaorrentenumero") String contaorrentenumero, @RequestParam (value = "valor") double valor)
    {
        Optional<Contacorrente> oldContaCorrente = _transacoesRepository.findByContaCorrenteNumero(contaorrentenumero);
        if(oldContaCorrente.isPresent()){
            Contacorrente contacorrente = oldContaCorrente.get();
            
            if (valor >0 && valor <= contacorrente.getContaCorrenteSaldo() && contacorrente.getContaCorrenteSaldo() >= 0) 
            {	
            	double saldo = contacorrente.getContaCorrenteSaldo() - valor;
            	contacorrente.setContaCorrenteSaldo(saldo);
            	_transacoesRepository.save(contacorrente);
            	
              	 return new ResponseEntity(Mensagem.TRSCSUCESSO.getDescricao(), HttpStatus.OK);
            }
            else
           	 return new ResponseEntity(Mensagem.SEMSALDO.getDescricao(), HttpStatus.NOT_ACCEPTABLE);
            }
        else
            return new ResponseEntity(Mensagem.DADOSN.getDescricao(),HttpStatus.NOT_FOUND);
        }

    
    
    //transferir pelo id
    @RequestMapping(value = "/transferir/{1 - Debitar da conta}/para/{2- Creditar na conta}", method =  RequestMethod.PUT)
    @ApiOperation(value="Transferir de uma conta para a outra através do id da conta ")
    public ResponseEntity<Contacorrente> Transferir(@PathVariable(value = "1 - Debitar da conta")  long contaorrentenumero, @PathVariable(value = "2- Creditar na conta")  long contaorrentenumero2, @RequestParam (value = "valor") double valor)
    {
    	 Optional<Contacorrente> oldContaCorrente = _transacoesRepository.findById(contaorrentenumero);
    	 Optional<Contacorrente> segContaCorrente = _transacoesRepository.findById(contaorrentenumero);

    	 
         if(oldContaCorrente.isPresent() && segContaCorrente.isPresent() && valor > 0){
        	 Contacorrente contacorrentep = oldContaCorrente.get();
        	 Contacorrente contacorrentes = segContaCorrente.get();
        	 
             
             if (valor <= contacorrentep.getContaCorrenteSaldo() && contacorrentep.getContaCorrenteSaldo() >= 0) 
             {	
             	double saldoe = contacorrentep.getContaCorrenteSaldo() - valor;
             	double saldor = contacorrentes.getContaCorrenteSaldo() + valor;
             	
             	contacorrentep.setContaCorrenteSaldo(saldoe);
             	_transacoesRepository.save(contacorrentep);
             	contacorrentes.setContaCorrenteSaldo(saldor);
             	_transacoesRepository.save(contacorrentes);
             	             	
             	 return new ResponseEntity(Mensagem.TRSCSUCESSO.getDescricao(), HttpStatus.OK);
             }
             else
            	 return new ResponseEntity(Mensagem.SEMSALDO.getDescricao(), HttpStatus.NOT_ACCEPTABLE);
             }
         else
             return new ResponseEntity(Mensagem.DADOSN.getDescricao(),HttpStatus.NOT_FOUND);
    }
    

    //transferir pelo Numero da conta
    @RequestMapping(value = "/transferir/numerodaconta", method =  RequestMethod.PUT)
    @ApiOperation(value="Transferir de uma conta para a outra através do numero da conta ")
    public ResponseEntity<Contacorrente> Transferirr(@RequestParam(value = "1 - Debitar da conta")  String contaorrentenumero, @RequestParam(value = "2- Creditar na conta")  String contaorrentenumero2, @RequestParam (value = "valor") double valor)
    {

    	 Optional<Contacorrente> oldContaCorrente = _transacoesRepository.findByContaCorrenteNumero(contaorrentenumero);
    	 Optional<Contacorrente> segContaCorrente = _transacoesRepository.findByContaCorrenteNumero(contaorrentenumero2);

    	 
         if(oldContaCorrente.isPresent() && segContaCorrente.isPresent() && valor > 0){
        	 Contacorrente contacorrentep = oldContaCorrente.get();
        	 Contacorrente contacorrentes = segContaCorrente.get();
        	 
             
             if (valor <= contacorrentep.getContaCorrenteSaldo() && contacorrentep.getContaCorrenteSaldo() >= 0) 
             {	
             	double saldoe = contacorrentep.getContaCorrenteSaldo() - valor;
             	double saldor = contacorrentes.getContaCorrenteSaldo() + valor;
             	
             	contacorrentep.setContaCorrenteSaldo(saldoe);
             	_transacoesRepository.save(contacorrentep);
             	contacorrentes.setContaCorrenteSaldo(saldor);
             	_transacoesRepository.save(contacorrentes);
             	         	
             	 return new ResponseEntity(Mensagem.TRSCSUCESSO.getDescricao(), HttpStatus.OK);
             }
             else {
            	 	
            	 return new ResponseEntity(Mensagem.SEMSALDO.getDescricao(), HttpStatus.NOT_ACCEPTABLE);
            	//return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
             }
         }
         else
             return new ResponseEntity(Mensagem.DADOSN.getDescricao(),HttpStatus.NOT_FOUND);
    }
    
    
    
    //Saldo pelo id
    @RequestMapping(value = "/saldo/{idcontacorrente}", method = RequestMethod.GET)
    @ApiOperation(value="Mostra saldo da conta através do id")
    public ResponseEntity<Contacorrente> Saldo(@PathVariable(value = "idcontacorrente") long idcontacorrente)
    {
        Optional<Contacorrente> contacorrente = _transacoesRepository.findById(idcontacorrente);
        if(contacorrente.isPresent())
            return new ResponseEntity<Contacorrente>(contacorrente.get(), HttpStatus.OK);
        else
            return new ResponseEntity(Mensagem.DADOSN.getDescricao(),HttpStatus.NOT_FOUND);
        }

    
    
    //Saldo pelo numero
    @RequestMapping(value = "/saldo/{contaorrentenumero}", method = RequestMethod.GET)
    @ApiOperation(value="Mostra saldo da conta através do numero")
    public ResponseEntity<Contacorrente> SaldoN(@PathVariable(value = "contaorrentenumero") String contaorrentenumero)
    {
        Optional<Contacorrente> contacorrente = _transacoesRepository.findByContaCorrenteNumero(contaorrentenumero);
        if(contacorrente.isPresent())
            return new ResponseEntity<Contacorrente>(contacorrente.get(), HttpStatus.OK);
        else
            return new ResponseEntity(Mensagem.DADOSN.getDescricao(),HttpStatus.NOT_FOUND);
        }

}