package com.task.logistic.controller.rest;

import com.task.logistic.entity.Client;
import com.task.logistic.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientRestController {
    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/get-by-name")
    public ResponseEntity<Client> getByNameAndLastName(@RequestParam(value = "name") String name,
                                                       @RequestParam(value = "lastname") String lastName) {
        Client c = clientService.getByNameAndLastName(name, lastName);
        if (c != null) {
            return new ResponseEntity<>(c, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Client>> getAll() {
        List<Client> clients = clientService.getAllClients();
        if (!clients.isEmpty()) {
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<Client> getById(@RequestParam("id") Long id) {
        Client client = clientService.getById(id);
        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Client> save(@Valid @RequestBody Client client, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            clientService.save(client);
            return new ResponseEntity<>(client, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Client> delete(@RequestParam("id") Long id) {
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
