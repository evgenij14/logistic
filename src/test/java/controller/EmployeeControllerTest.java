package controller;

import com.task.logistic.controller.EmployeeRestController;
import com.task.logistic.entity.Employee;
import com.task.logistic.service.EmployeeService;
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
public class EmployeeControllerTest {
    @Mock
    private EmployeeService employeeService;

    @Mock
    private Employee employee;

    @Mock
    private List<Employee> employees;

    @Mock
    private Errors errors;

    @InjectMocks
    private EmployeeRestController employeeRestController;

    @Test
    public void getAllNotNullTest() {
        when(employeeService.getAllEmployees()).thenReturn(employees);
        ResponseEntity<List<Employee>> out = employeeRestController.getAll();
        assertNotNull(out.getBody());
        assertEquals(out.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getAllNullTest() {
        when(employeeService.getAllEmployees()).thenReturn(null);
        ResponseEntity<List<Employee>> out = employeeRestController.getAll();
        assertNull(out.getBody());
        assertEquals(out.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void successGetByIdTest() {
        when(employeeService.getById(anyLong())).thenReturn(employee);
        ResponseEntity<Employee> out = employeeRestController.getById(anyLong());
        assertNotNull(out.getBody());
        assertEquals(out.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void failGetByIdTest() {
        when(employeeService.getById(anyLong())).thenReturn(null);
        ResponseEntity<Employee> out = employeeRestController.getById(anyLong());
        assertNull(out.getBody());
        assertEquals(out.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void successGetByNameAndLastnameTest() {
        when(employeeService.getByNameAndLastName(anyString(), anyString())).thenReturn(employee);
        ResponseEntity<Employee> out = employeeRestController.getByNameAndLastName(anyString(), anyString());
        assertNotNull(out.getBody());
        assertEquals(out.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void failGetByNameAndLastnameTest() {
        when(employeeService.getByNameAndLastName(anyString(), anyString())).thenReturn(null);
        ResponseEntity<Employee> out = employeeRestController.getByNameAndLastName(anyString(), anyString());
        assertNull(out.getBody());
        assertEquals(out.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void successSaveTest() {
        when(errors.hasErrors()).thenReturn(false);
        ResponseEntity<Employee> out = employeeRestController.save(employee, errors);
        verify(employeeService).save(employee);
        assertNotNull(out.getBody());
        assertEquals(out.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void failSaveTest() {
        when(errors.hasErrors()).thenReturn(true);
        ResponseEntity<Employee> out = employeeRestController.save(employee, errors);
        verify(employeeService, never()).save(employee);
        assertNull(out.getBody());
        assertEquals(out.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void deleteTest() {
        ResponseEntity<Employee> out = employeeRestController.delete(anyLong());
        verify(employeeService).delete(anyLong());
        assertEquals(out.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void successGetByPassport() {
        when(employeeService.getByPassport(anyString())).thenReturn(employee);
        ResponseEntity<Employee> out = employeeRestController.getByPassport(anyString());
        assertNotNull(out.getBody());
        assertEquals(out.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void failGetByPassport() {
        when(employeeService.getByPassport(anyString())).thenReturn(null);
        ResponseEntity<Employee> out = employeeRestController.getByPassport(anyString());
        assertNull(out.getBody());
        assertEquals(out.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
