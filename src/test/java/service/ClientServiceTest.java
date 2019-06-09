package service;

import com.task.logistic.entity.Client;
import com.task.logistic.repository.ClientRepository;
import com.task.logistic.service.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private Client client;

    @InjectMocks
    private ClientService clientService;

    @Test
    public void getByIdTest() {
        clientService.getById(anyLong());
        verify(clientRepository).findById(anyLong());
    }

    @Test
    public void getByNameAndLastNameTest() {
        clientService.getByNameAndLastName("name", "lastname");
        verify(clientRepository).findClientByNameAndLastname("name", "lastname");
    }

    @Test
    public void deleteTest() {
        clientService.delete(anyLong());
        verify(clientRepository).deleteById(anyLong());
    }

    @Test
    public void getAllClientsTest() {
        clientService.getAllClients();
        verify(clientRepository).findAll();
    }

    @Test
    public void saveTest() {
        clientService.save(client);
        verify(clientRepository).save(client);
    }
}
