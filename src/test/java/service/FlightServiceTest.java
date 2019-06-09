package service;

import com.task.logistic.entity.Flight;
import com.task.logistic.repository.FlightRepository;
import com.task.logistic.service.FlightService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private Flight flight;

    @InjectMocks
    private FlightService flightService;

    @Test
    public void fetByIdTest() {
        flightService.getById(anyLong());
        verify(flightRepository).findById(anyLong());
    }

    @Test
    public void deleteTest() {
        flightService.delete(anyLong());
        verify(flightRepository).deleteById(anyLong());
    }

    @Test
    public void getFlightByClientIdTest() {
        flightService.getFlightByClientId(anyLong());
        verify(flightRepository).findByClientId(anyLong());
    }

    @Test
    public void getAllFlightsTest() {
        flightService.getAllFlights();
        verify(flightRepository).findAll();
    }

    @Test
    public void saveTest() {
        flightService.save(flight);
        verify(flightRepository).save(flight);
    }
}
