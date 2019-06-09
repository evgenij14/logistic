package service;

import com.task.logistic.entity.Vehicle;
import com.task.logistic.repository.VehicleRepository;
import com.task.logistic.service.VehicleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private Vehicle vehicle;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    public void getByIdTest() {
        vehicleService.getById(anyLong());
        verify(vehicleRepository).findById(anyLong());
    }

    @Test
    public void getByNumberplateTest() {
        vehicleService.getByNumberPlate("number");
        verify(vehicleRepository).getByNumberplate("number");
    }

    @Test
    public void deleteTest() {
        vehicleService.delete(anyLong());
        verify(vehicleRepository).deleteById(anyLong());
    }

    @Test
    public void getAllVehiclesTest() {
        vehicleService.getAllVehicles();
        verify(vehicleRepository).findAll();
    }

    @Test
    public void save() {
        vehicleService.save(vehicle);
        verify(vehicleRepository).save(vehicle);
    }
}
