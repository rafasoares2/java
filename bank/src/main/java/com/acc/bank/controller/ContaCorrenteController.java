package com.acc.bank.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.acc.bank.constantes.Mensagem;
import com.acc.bank.entity.Contacorrente;
import com.acc.bank.repository.ContaCorrenteRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
@Api(value="Conta Corrente")
@CrossOrigin(origins="*")

public class ContaCorrenteController {
    @Autowired
    private ContaCorrenteRepository _contaCorrenteRepository;
    
    
    
    //trazer todas as contas
    @RequestMapping(value = "/contacorrente", method = RequestMethod.GET)
    @ApiOperation(value="Consultar todas as contas")
    public List<Contacorrente> Get() {
        return _contaCorrenteRepository.findAll();
    }

    
    
    //trazer a conta pelo numero.
    @ApiOperation(value="Consultar conta pelo numero")
    @RequestMapping(value = "/contacorrente/{contaorrentenumero}", method = RequestMethod.GET)
    public ResponseEntity<Contacorrente> GetById(@PathVariable(value = "contaorrentenumero") String contaorrentenumero)
    {
        Optional<Contacorrente> contacorrente = _contaCorrenteRepository.findByContaCorrenteNumero(contaorrentenumero);
        if(contacorrente.isPresent())
            return new ResponseEntity<Contacorrente>(contacorrente.get(), HttpStatus.OK);
        else
            return new ResponseEntity(Mensagem.DADOSN.getDescricao(),HttpStatus.NOT_FOUND);
    }
    
    
    
    //trazer a conta pelo id do cliente 
    @RequestMapping(value = "/contacorrente/cliente/{idcliente}", method = RequestMethod.GET)
    @ApiOperation(value="Consultar a conta pelo id do cliente")
    public ResponseEntity<Contacorrente> GetById1(@PathVariable(value = "idcliente") long idcliente)
    {
        Optional<Contacorrente> contacorrente = _contaCorrenteRepository.findById(idcliente);
        if(contacorrente.isPresent())
            return new ResponseEntity<Contacorrente>(contacorrente.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    
    
    
    //Cadastrar a conta do cliente
    @RequestMapping(value = "/contacorrente", method =  RequestMethod.POST)
    @ApiOperation(value="Cadastrar a conta do cliente")
    public Contacorrente Post(@Valid @RequestBody Contacorrente contacorrente)
    {
        return _contaCorrenteRepository.save(contacorrente);
    }

    
    
    //atualizar os dados da conta pelo id da conta
    @RequestMapping(value = "/contacorrente/{idcontacorrente}", method =  RequestMethod.PUT)
    @ApiOperation(value="Atualizar os dados da conta pelo id da conta")
    public ResponseEntity<Contacorrente> Put(@PathVariable(value = "idcontacorrente") long idcontacorrente, @Valid @RequestBody Contacorrente newContaCorrente)
    {
        Optional<Contacorrente> oldContaCorrente = _contaCorrenteRepository.findById(idcontacorrente);
        if(oldContaCorrente.isPresent()){
            Contacorrente contacorrente = oldContaCorrente.get();
            contacorrente.setContaCorrenteAgencia(newContaCorrente.getContaCorrenteAgencia());
            contacorrente.setContaCorrenteNumero(newContaCorrente.getContaCorrenteNumero());
            contacorrente.setIdCliente(newContaCorrente.getIdCliente());
            _contaCorrenteRepository.save(contacorrente);
            return new ResponseEntity<Contacorrente>(contacorrente, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
    
    //Deletar a conta corrente pelo id
    @RequestMapping(value = "/contacorrente/{idcontacorrente}", method = RequestMethod.DELETE)
    @ApiOperation(value="Deletar conta pelo id da conta")
    public ResponseEntity<Object> Delete(@PathVariable(value = "idcontacorrente") long idcontacorrente)
    {
        Optional<Contacorrente> contacorrente = _contaCorrenteRepository.findById(idcontacorrente);
        if(contacorrente.isPresent()){
            _contaCorrenteRepository.delete(contacorrente.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}