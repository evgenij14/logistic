package com.task.logistic.service;

import com.task.logistic.repository.ClientRepository;
import entity.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client getById(Long id) {
        return clientRepository.getOne(id);
    }

    public Client getByNameAndLastName(String name, String lastName) {
        return clientRepository.findClientByNameAndLastname(name, lastName);
    }

    public void add(String name, String lastName, String address, int payment) {
        clientRepository.save(new Client(name, lastName, address, payment, null));
    }

    public void delete(Long id) {
        clientRepository.delete(clientRepository.getOne(id));
    }

}
