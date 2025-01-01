package model;

public class Employee {
    private final String name;
    private final int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String toString() {
        return "Employee{" + "name='" + name + '\'' + ", salary=" + salary + '}';
    }

    public int getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }
}
