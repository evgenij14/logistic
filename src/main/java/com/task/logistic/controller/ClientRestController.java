package com.task.logistic.controller;

import com.task.logistic.entity.Client;
import com.task.logistic.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для обслуживания таблицы клиентов
 * (Controller for service the table of clients)
 * Принимает запросы на url : "/client" (Accepts requests on url : "/client")
 */
@RestController
@RequestMapping("/client")
public class ClientRestController {
    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Поиск клиента по имени и фамилии(Searching the client by name and surname)
     *
     * @param name     Имя искомого клиента(The name of searching client)
     * @param lastName Фамилия искомого клиента(The surname of searching client)
     * @return Возвращает найденного клиента в формате JSON + статус ответа(Return searched client on JSON + response status)
     */
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

    /**
     * Поиск всех клиентов в БД(Searching all clients in Database)
     *
     * @return Список клиентов в формате JSON + статус ответа(The List of clients on JSON + response status)
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<Client>> getAll() {
        List<Client> clients = clientService.getAllClients();
        if (!clients.isEmpty()) {
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Поиск клиента по id (Searching the client by id)
     * @param id ID клиента (Client's ID)
     * @return Найденный клиент в формате JSON + статус ответа(Return searched client on JSON + response status)
     */
    @GetMapping("/get-by-id")
    public ResponseEntity<Client> getById(@RequestParam("id") Long id) {
        Client client = clientService.getById(id);
        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Сохранение клиента в базу данных (Saving the client in Database)
     * @param client Объект клиента, сформированный на front-end (The object of client, which is formed on front-end)
     * @param errors Возможные ошибки, которые могут возникнуть при формировании объекта клиента
     *               (Possible errors that may occur during the formation of the client object)
     * @return Возвращает сохраненного клиента в формате JSON + статус ответа (Return saved client on JSON + response status)
     */
    @PostMapping("/save")
    public ResponseEntity<Client> save(@Valid @RequestBody Client client, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            clientService.save(client);
            return new ResponseEntity<>(client, HttpStatus.CREATED);
        }
    }

    /**
     * Удаление клиента из БД (Deleting the client from Database)
     * @param id ID клиента (Client's id)
     * @return Статус ответа (Response status)
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Client> delete(@RequestParam("id") Long id) {
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
