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

    /**
     * Поиск клиента по id (Searching the client by id)
     *
     * @param id ID клиента (Client's ID)
     * @return Объект клиента(Return searched client)
     */
    @Transactional
    public Client getById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    /**
     * Поиск клиента по имени и фамилии(Searching the client by name and surname)
     *
     * @param name     Имя искомого клиента(The name of searching client)
     * @param lastName Фамилия искомого клиента(The surname of searching client)
     * @return Возвращает объект клиента(Return the object of client)
     */
    @Transactional
    public Client getByNameAndLastName(String name, String lastName) {
        return clientRepository.findClientByNameAndLastname(name, lastName);
    }

    /**
     * Удаление клиента из БД (Deleting the client from Database)
     * @param id ID клиента (Client's id)
     */
    @Transactional
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    /**
     * Поиск всех клиентов в БД(Searching all clients in Database)
     * @return Список клиентов(The List of clients)
     */
    @Transactional
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    /**
     * Сохранение клиента (saving of client)
     * @param client Объект клиента для сохранения (The object of client for saving)
     */
    @Transactional
    public void save(Client client) {
        clientRepository.save(client);
    }

}
