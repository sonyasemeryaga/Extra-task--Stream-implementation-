import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import java.util.List;
import repository.EmployeeRepository;
import stream.collector.CustomCollector;
import model.Employee;
import stream.Stream;

public class MainTest {

    EmployeeRepository employeeRepository = new EmployeeRepository();

    @BeforeEach
    public void setUp() {
        employeeRepository.addEmployee(1, new Employee("John", 50000));
        employeeRepository.addEmployee(2, new Employee("Janette", 300000));
        employeeRepository.addEmployee(3, new Employee("Alic", 150000));
        employeeRepository.addEmployee(4, new Employee("Bob", 100000));
    }

    @Test
    public void whenFilterEmployees_thenGetFilteredStream() {
        Integer[] empIds = {1, 2, 3, 4};

        List<Employee> employees = Stream.of(empIds)
                .map(employeeRepository::findById)
                .filter(e -> e != null)
                .filter(e -> e.getSalary() > 200000)
                .collect(CustomCollector.toList());

        assertEquals(Arrays.asList(employeeRepository.findById(2)), employees);
    }

    @Test
    public void whenAddNewEmployee_thenGetUpdatedFilteredStream() {
        Integer[] empIds = {1, 2, 3, 4, 5};

        employeeRepository.addEmployee(5, new Employee("Kile", 5500000));

        List<Employee> employees = Stream.of(empIds)
                .map(employeeRepository::findById)
                .filter(e -> e != null)
                .filter(e -> e.getSalary() > 200000)
                .collect(CustomCollector.toList());

        assertEquals(Arrays.asList(employeeRepository.findById(2), employeeRepository.findById(5)), employees);
    }

    @Test
    public void whenNoEmployeesMatch_thenGetEmptyStream() {
        Integer[] empIds = {1, 3, 4};

        List<Employee> employees = Stream.of(empIds)
                .map(employeeRepository::findById)
                .filter(e -> e != null)
                .filter(e -> e.getSalary() > 400000)
                .collect(CustomCollector.toList());

        assertTrue(employees.isEmpty());
    }

    @Test
    public void whenAllEmployeesMatch_thenGetAllEmployeesStream() {
        Integer[] empIds = {1, 2, 3, 4};

        employeeRepository.addEmployee(5, new Employee("Richie", 1000000));

        List<Employee> employees = Stream.of(empIds)
                .map(employeeRepository::findById)
                .filter(e -> e != null)
                .filter(e -> e.getSalary() >= 50000)
                .collect(CustomCollector.toList());

        assertEquals(
                Arrays.asList(
                        employeeRepository.findById(1),
                        employeeRepository.findById(2),
                        employeeRepository.findById(3),
                        employeeRepository.findById(4)
                ),
                employees
        );
    }

    @Test
    public void whenFilterByName_thenGetFilteredStream() {
        Integer[] empIds = {1, 2, 3, 4, 5};

        employeeRepository.addEmployee(5, new Employee("Alice", 200000));

        List<Employee> employees = Stream.of(empIds)
                .map(employeeRepository::findById)
                .filter(e -> e != null)
                .filter(e -> e.getName().startsWith("A"))
                .collect(CustomCollector.toList());

        assertEquals(Arrays.asList(employeeRepository.findById(3), employeeRepository.findById(5)), employees);
    }

    @Test
    public void whenNoIdsProvided_thenGetEmptyStream() {
        Integer[] empIds = {};

        List<Employee> employees = Stream.of(empIds)
                .map(employeeRepository::findById)
                .filter(e -> e != null)
                .collect(CustomCollector.toList());

        assertTrue(employees.isEmpty());
    }
}
