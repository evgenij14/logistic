package com.task.logistic.service;

import com.task.logistic.entity.Client;
import com.task.logistic.repository.ClientRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public Client getById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Transactional
    public Client getByNameAndLastName(String name, String lastName) {
        return clientRepository.findClientByNameAndLastname(name, lastName);
    }

    @Transactional
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Transactional
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Transactional
    public void save(Client client) {
        clientRepository.save(client);
    }

}
