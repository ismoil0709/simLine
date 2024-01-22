package uz.pdp.simline.service;

import uz.pdp.simline.entity.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    void save(Employee employee);
    List<Employee> getAll();
    Employee getById(UUID id);
    List<Employee> getAllByRole(String role);
    Employee getByEmail(String email);
    Employee getByName(String name);
    Employee getBySurname(String surname);
    Employee getByPhoneNumber(String phoneNumber);
    Employee getBySalary(Double salary);
    void update(Employee employee);
    void deleteAll();
    void deleteById(UUID id);
    void deleteAllByRole(String role);
    void deleteByEmail(String email);
    void deleteByName(String name);
    void deleteBySurname(String surname);
    void deleteByPhoneNumber(String phoneNumber);
}
