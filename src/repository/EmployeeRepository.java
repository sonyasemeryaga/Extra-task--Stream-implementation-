package repository;

import model.Employee;
import java.util.HashMap;
import java.util.Map;

public class EmployeeRepository {
    private final Map<Integer, Employee> rep = new HashMap<>();

    public void addEmployee(int id, Employee employee) {
        if (!rep.isEmpty() && rep.containsKey(id))
            System.out.println("Id already present, can't add");
        else
            rep.put(id, employee);
    }

    public Employee findById(int id) {
        return rep.get(id);
    }
}
