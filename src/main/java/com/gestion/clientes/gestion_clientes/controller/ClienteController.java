package com.gestion.clientes.gestion_clientes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.clientes.gestion_clientes.entities.Cliente;
import com.gestion.clientes.gestion_clientes.exception.ResourceNotFoundException;
import com.gestion.clientes.gestion_clientes.repositories.ClienteRepository;

@CrossOrigin(origins = "http://192.168.1.2:3000")
@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    @PostMapping("/clientes")
    public Cliente guardarCliente(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> listarClientesPorId(@PathVariable Long id){
        Cliente cliente = clienteRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Cliente con ID " + id + " no encontrado"));

        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteRequest){
        Cliente cliente = clienteRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Cliente con ID " + id + " no encontrado"));

        cliente.setNombre(clienteRequest.getNombre());
        cliente.setApellido(clienteRequest.getApellido());
        cliente.setEmail(clienteRequest.getEmail());

        Cliente clienteActuzalizado = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteActuzalizado);
    }


    @DeleteMapping("clientes/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCliente(@PathVariable Long id){
        Cliente cliente = clienteRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Cliente con ID " + id + " no encontrado"));

        clienteRepository.delete(cliente);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
