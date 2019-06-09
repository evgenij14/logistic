package com.task.logistic.repository;

import com.task.logistic.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findClientByNameAndLastname(String name, String lastName);
}
