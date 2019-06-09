package controller;

import com.task.logistic.controller.rest.ClientRestController;
import com.task.logistic.entity.Client;
import com.task.logistic.service.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @Mock
    private List<Client> clients;

    @Mock
    private Client client;

    @Mock
    private Errors errors;

    @InjectMocks
    private ClientRestController clientController;

    @Test
    public void successGetByNameAndLastnameTest() {
        when(clientService.getByNameAndLastName(anyString(), anyString())).thenReturn(client);
        ResponseEntity<Client> out = clientController.getByNameAndLastName(anyString(), anyString());
        assertEquals(out.getStatusCode(), HttpStatus.OK);
        assertNotNull(out.getBody());
    }

    @Test
    public void failGetNameAndLastnameTest() {
        when(clientService.getByNameAndLastName(anyString(), anyString())).thenReturn(null);
        ResponseEntity<Client> out = clientController.getByNameAndLastName(anyString(), anyString());
        assertEquals(out.getStatusCode(), HttpStatus.NOT_FOUND);
        assertNull(out.getBody());
    }

    @Test
    public void successGetAllTest() {
        when(clientService.getAllClients()).thenReturn(clients);
        when(clients.isEmpty()).thenReturn(false);
        ResponseEntity<List<Client>> out = clientController.getAll();
        assertNotNull(out.getBody());
        assertEquals(out.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void failGetAllTest() {
        when(clientService.getAllClients()).thenReturn(clients);
        when(clients.isEmpty()).thenReturn(true);
        ResponseEntity<List<Client>> out = clientController.getAll();
        assertNull(out.getBody());
        assertEquals(out.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void successByIdTest() {
        when(clientService.getById(anyLong())).thenReturn(client);
        ResponseEntity<Client> out = clientController.getById(anyLong());
        assertEquals(out.getStatusCode(), HttpStatus.OK);
        assertNotNull(out.getBody());
    }

    @Test
    public void failByIdTest() {
        when(clientService.getById(anyLong())).thenReturn(null);
        ResponseEntity<Client> out = clientController.getById(anyLong());
        assertEquals(out.getStatusCode(), HttpStatus.NOT_FOUND);
        assertNull(out.getBody());
    }

    @Test
    public void successSaveTest() {
        when(errors.hasErrors()).thenReturn(false);
        ResponseEntity<Client> out = clientController.save(client, errors);
        verify(clientService).save(client);
        assertEquals(out.getStatusCode(), HttpStatus.CREATED);
        assertNotNull(out.getBody());
    }

    @Test
    public void failSaveTest() {
        when(errors.hasErrors()).thenReturn(true);
        ResponseEntity<Client> out = clientController.save(client, errors);
        verify(clientService, never()).save(client);
        assertEquals(out.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNull(out.getBody());
    }

    @Test
    public void deleteTest() {
        ResponseEntity<Client> out = clientController.delete(anyLong());
        verify(clientService).delete(anyLong());
        assertEquals(out.getStatusCode(), HttpStatus.OK);
        assertNull(out.getBody());
    }
}
