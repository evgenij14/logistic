package controller;

import com.task.logistic.controller.VehicleRestController;
import com.task.logistic.entity.Vehicle;
import com.task.logistic.service.VehicleService;
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
public class VehicleControllerTest {
    private VehicleService vehicleService = mock(VehicleService.class);

    @Mock
    private Errors errors = mock(Errors.class);

    @Mock
    private Vehicle vehicle;

    @Mock
    private List<Vehicle> vehicles;

    @InjectMocks
    private VehicleRestController vehicleRestController;

    @Test
    public void failGetByIdTest() {
        when(vehicleService.getById(anyLong())).thenReturn(null);
        ResponseEntity<Vehicle> out = vehicleRestController.getById(anyLong());
        assertEquals(out.getStatusCode(), HttpStatus.NOT_FOUND);
        assertNull(out.getBody());
    }

    @Test
    public void successGetByIdTest() {
        when(vehicleService.getById(anyLong())).thenReturn(vehicle);
        ResponseEntity<Vehicle> out = vehicleRestController.getById(anyLong());
        assertEquals(out.getStatusCode(), HttpStatus.OK);
        assertNotNull(out.getBody());
    }

    @Test
    public void notNullAllListTest() {
        when(vehicleService.getAllVehicles()).thenReturn(vehicles);
        when(vehicles.isEmpty()).thenReturn(false);
        ResponseEntity<List<Vehicle>> out = vehicleRestController.getAll();
        assertNotNull(out.getBody());
        assertEquals(out.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void nullAllListTest() {
        when(vehicleService.getAllVehicles()).thenReturn(vehicles);
        when(vehicles.isEmpty()).thenReturn(true);
        ResponseEntity<List<Vehicle>> out = vehicleRestController.getAll();
        assertNull(out.getBody());
        assertEquals(out.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void successGetByNumberplateTest() {
        when((vehicleService.getByNumberPlate(anyString()))).thenReturn(vehicle);
        ResponseEntity<Vehicle> out = vehicleRestController.getByNumberPlate(anyString());
        assertNotNull(out.getBody());
        assertEquals(out.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void nullGetByNumberplateTest() {
        when((vehicleService.getByNumberPlate(anyString()))).thenReturn(null);
        ResponseEntity<Vehicle> out = vehicleRestController.getByNumberPlate(anyString());
        assertNull(out.getBody());
        assertEquals(out.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void successSaveTest() {
        when(errors.hasErrors()).thenReturn(false);
        ResponseEntity<Vehicle> out = vehicleRestController.save(vehicle, errors);
        verify(vehicleService).save(vehicle);
        assertNotNull(out.getBody());
        assertEquals(out.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void failSaveTest() {
        when(errors.hasErrors()).thenReturn(true);
        ResponseEntity<Vehicle> out = vehicleRestController.save(vehicle, errors);
        verify(vehicleService, never()).save(vehicle);
        assertNull(out.getBody());
        assertEquals(out.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void deleteTest() {
        ResponseEntity<Vehicle> out = vehicleRestController.delete(anyLong());
        verify(vehicleService).delete(anyLong());
        assertEquals(out.getStatusCode(), HttpStatus.OK);
    }
}
