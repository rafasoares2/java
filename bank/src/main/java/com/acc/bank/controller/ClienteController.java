package com.acc.bank.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acc.bank.constantes.Mensagem;
import com.acc.bank.entity.Cliente;
import com.acc.bank.repository.ClienteRepository;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@RestController
public class ClienteController {
	
    @Autowired
    private ClienteRepository _clienteRepository;

    
    //Consultar todos os clientes
    @RequestMapping(value = "/cliente", method = RequestMethod.GET)
    @ApiOperation(value="Consultar todas os clientes")
    public List<Cliente> Get() {
        return _clienteRepository.findAll();
    }
    
    

    //Consultar cliente pelo id
    @RequestMapping(value = "/cliente/{idcliente}", method = RequestMethod.GET)
    @ApiOperation(value="Localizar cliente pelo id do cliente")
    public ResponseEntity<Cliente> GetById(@PathVariable(value = "idcliente") long idcliente)
    {
        Optional<Cliente> cliente = _clienteRepository.findById(idcliente);
        if(cliente.isPresent())
            return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
        else
         	 return new ResponseEntity(Mensagem.CLTNENC.getDescricao(), HttpStatus.NOT_ACCEPTABLE);
    }
    

    
    //Consultar cliente pelo CPF
    @RequestMapping(value = "/cliente/consultarcpf", method = RequestMethod.GET)
    @ApiOperation(value="Localizar cliente pelo CPF do cliente")
    public ResponseEntity<Cliente> GetByClienteCpf(@RequestParam(value = "clienteCpf") String clienteCpf)
    {
        Optional<Cliente> cliente = _clienteRepository.findByClienteCpf(clienteCpf);
        if(cliente.isPresent())
            return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
        else
         	 return new ResponseEntity(Mensagem.CLTNENC.getDescricao(), HttpStatus.NOT_ACCEPTABLE);
    }
    
    
    
    //cadastrar novo cliente
    @RequestMapping(value = "/cliente", method =  RequestMethod.POST)
    @ApiOperation(value="Cadastrar o cliente")
    public Cliente Post(@Valid @RequestBody Cliente cliente)
    {
        return _clienteRepository.save(cliente);
    }
    
    
    
    //Atualizar os dados do cliente através do id
    @RequestMapping(value = "/cliente/{idcliente}", method =  RequestMethod.PUT)
    @ApiOperation(value="Atualizar os dados do cliente através do id")
    public ResponseEntity<Cliente> Put(@PathVariable(value = "idcliente") long idcliente, @Valid @RequestBody Cliente newCliente)
    {
        Optional<Cliente> oldCliente = _clienteRepository.findById(idcliente);
        if(oldCliente.isPresent()){
            Cliente cliente = oldCliente.get();
            cliente.setClienteNome(newCliente.getClienteNome());
            cliente.setClienteCpf(newCliente.getClienteCpf());
            cliente.setClienteFone(newCliente.getClienteFone());
            _clienteRepository.save(cliente);
            
            return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
        }
        else
        	 return new ResponseEntity(Mensagem.CLTNENC.getDescricao(), HttpStatus.NOT_ACCEPTABLE);
    }
    
    

    //Excluir o cliente
    @RequestMapping(value = "/cliente/{idcliente}", method = RequestMethod.DELETE)
    @ApiOperation(value="Excluir o cliente através do id")
    public ResponseEntity<Object> Delete(@PathVariable(value = "idcliente") long idcliente)
    {
        Optional<Cliente> cliente = _clienteRepository.findById(idcliente);
        if(cliente.isPresent()){
            _clienteRepository.delete(cliente.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
       	 return new ResponseEntity(Mensagem.CLTNENC.getDescricao(), HttpStatus.NOT_ACCEPTABLE);
    }
}