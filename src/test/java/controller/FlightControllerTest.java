package controller;

import com.task.logistic.controller.rest.FlightRestController;
import com.task.logistic.entity.Flight;
import com.task.logistic.service.FlightService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FlightControllerTest {
    @Mock
    private FlightService flightService;

    @Mock
    private List<Flight> flights;

    @Mock
    private Flight flight;

    @InjectMocks
    private FlightRestController flightController;

    @Test
    public void successGetAllTest() {
        when(flightService.getAllFlights()).thenReturn(flights);
        when(flights.isEmpty()).thenReturn(false);
        ResponseEntity<List<Flight>> out = flightController.getAll();
        assertEquals(out.getStatusCode(), HttpStatus.OK);
        assertNotNull(out.getBody());
    }

    @Test
    public void failGetAllTest() {
        when(flightService.getAllFlights()).thenReturn(flights);
        when(flights.isEmpty()).thenReturn(true);
        ResponseEntity<List<Flight>> out = flightController.getAll();
        assertEquals(out.getStatusCode(), HttpStatus.NOT_FOUND);
        assertNull(out.getBody());
    }

    @Test
    public void successGetByIdTest() {
        when(flightService.getById(anyLong())).thenReturn(flight);
        ResponseEntity<Flight> out = flightController.getById(anyLong());
        assertEquals(out.getStatusCode(), HttpStatus.OK);
        assertNotNull(out.getBody());
    }

    @Test
    public void failGetByIdTest() {
        when(flightService.getById(anyLong())).thenReturn(null);
        ResponseEntity<Flight> out = flightController.getById(anyLong());
        assertEquals(out.getStatusCode(), HttpStatus.NOT_FOUND);
        assertNull(out.getBody());
    }

    @Test
    public void successGetByClientIdTest() {
        when(flightService.getFlightByClientId(anyLong())).thenReturn(flight);
        ResponseEntity<Flight> out = flightController.getByClientId(anyLong());
        assertEquals(out.getStatusCode(), HttpStatus.OK);
        assertNotNull(out.getBody());
    }

    @Test
    public void failGetByClientIdTest() {
        when(flightService.getFlightByClientId(anyLong())).thenReturn(null);
        ResponseEntity<Flight> out = flightController.getByClientId(anyLong());
        assertEquals(out.getStatusCode(), HttpStatus.NOT_FOUND);
        assertNull(out.getBody());
    }

    @Test
    public void saveTest() {
        ResponseEntity<Flight> out = flightController.save(12345L, 12345L, 12345L,
                Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
        verify(flightService).save(any(Flight.class));
        assertEquals(out.getStatusCode(), HttpStatus.CREATED);
        assertNotNull(out.getBody());
    }

    @Test
    public void deleteTest() {
        ResponseEntity<Flight> out = flightController.delete(anyLong());
        verify(flightService).delete(anyLong());
        assertEquals(out.getStatusCode(), HttpStatus.OK);
    }
}
