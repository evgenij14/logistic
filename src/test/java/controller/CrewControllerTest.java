package controller;

import com.task.logistic.controller.rest.CrewRestController;
import com.task.logistic.entity.Crew;
import com.task.logistic.entity.Employee;
import com.task.logistic.service.CrewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CrewControllerTest {

    @Mock
    private CrewService crewService;

    @Mock
    private List<Crew> crews;

    @InjectMocks
    private CrewRestController crewController;

    @Test
    public void getAllNotNullTest() {
        when(crewService.getAllCrew()).thenReturn(crews);
        when(crews.isEmpty()).thenReturn(false);
        ResponseEntity<List<Crew>> out = crewController.getAll();
        assertNotNull(out.getBody());
        assertEquals(out.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getAllNullTest() {
        when(crewService.getAllCrew()).thenReturn(crews);
        when(crews.isEmpty()).thenReturn(true);
        ResponseEntity<List<Crew>> out = crewController.getAll();
        assertNull(out.getBody());
        assertEquals(out.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void saveTest() {
        ResponseEntity<List<Employee>> out = crewController.save("name1", "name2", "lastName1", "lastName2");
        verify(crewService).save("name1", "name2", "lastName1", "lastName2");
        assertEquals(out.getStatusCode(), HttpStatus.CREATED);
        assertNull(out.getBody());
    }

    @Test
    public void deleteTest() {
        ResponseEntity<Crew> out = crewController.delete(anyLong());
        verify(crewService).delete(anyLong());
        assertEquals(out.getStatusCode(), HttpStatus.OK);
    }
}
