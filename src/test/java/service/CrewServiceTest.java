package service;

import com.task.logistic.entity.Crew;
import com.task.logistic.entity.Employee;
import com.task.logistic.repository.CrewRepository;
import com.task.logistic.repository.EmployeeRepository;
import com.task.logistic.service.CrewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class CrewServiceTest {

    @Mock
    private CrewRepository crewRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private Employee employee;

    @InjectMocks
    private CrewService crewService;

    @Test
    public void getAllCrewTest() {
        crewService.getAllCrew();
        verify(crewRepository).findAll();
    }

    @Test
    public void getByIdTest() {
        crewService.getById(anyLong());
        verify(crewRepository).findById(anyLong());
    }

    @Test
    public void deleteTest() {
        when(employee.getName()).thenReturn("name");
        when(employee.getLastName()).thenReturn("lastname");
        List<Employee> test = new ArrayList<>();
        test.add(employee);
        test.add(employee);
        when(employeeRepository.getEmployeeOfCrew(anyLong())).thenReturn(test);

        crewService.delete(anyLong());
        verify(employeeRepository, times(2)).deletingCrew("name", "lastname");
        verify(crewRepository).deleteById(anyLong());
    }

    @Test
    public void saveTest() {
        crewService.save("any", "any", "any", "any");
        verify(employeeRepository, times(2)).update(null, "any", "any");
        verify(crewRepository).save(any(Crew.class));
    }
}
