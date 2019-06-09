package service;

import com.task.logistic.entity.Crew;
import com.task.logistic.entity.Employee;
import com.task.logistic.repository.EmployeeRepository;
import com.task.logistic.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private Employee employee;

    @Mock
    private Crew crew;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void getByIdTest() {
        employeeService.getById(anyLong());
        verify(employeeRepository).findById(anyLong());
    }

    @Test
    public void getByNameAndLastNameTest() {
        employeeService.getByNameAndLastName("name", "lastname");
        verify(employeeRepository).findByNameAndLastName("name", "lastname");
    }

    @Test
    public void getByPassportTest() {
        employeeService.getByPassport("passport");
        verify(employeeRepository).findByPassport("passport");
    }

    @Test
    public void getAllEmployeesTest() {
        employeeService.getAllEmployees();
        verify(employeeRepository).findAll();
    }

    @Test
    public void saveTest() {
        employeeService.save(employee);
        verify(employeeRepository).save(employee);
    }

    @Test
    public void updateTest() {
        when(crew.getId()).thenReturn(123L);
        employeeService.update("name", "lastname", crew);
        verify(employeeRepository).update(crew.getId(), "name", "lastname");
    }

    @Test
    public void deletingCrewTest() {
        employeeService.deletingCrew("name", "lastname");
        verify(employeeRepository).deletingCrew("name", "lastname");
    }
}
