package uz.pdp.simline.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.simline.entity.Employee;
import uz.pdp.simline.exception.NotFoundException;
import uz.pdp.simline.exception.NullOrEmptyException;
import uz.pdp.simline.repository.EmployeeRepository;
import uz.pdp.simline.service.EmployeeService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public void save(Employee employee) {
        if (employee == null)
            throw new NullOrEmptyException("Employee");
        if (employee.getEmail() == null || employee.getEmail().isEmpty() || employee.getEmail().isBlank())
            throw new NullOrEmptyException("Employee's email");
        if (employee.getPhoneNumber() == null || employee.getPhoneNumber().isEmpty() || employee.getPhoneNumber().isBlank())
            throw new NullOrEmptyException("Employee's phone number");
        if (employee.getName() == null || employee.getName().isEmpty() || employee.getName().isBlank())
            throw new NullOrEmptyException("Employee's name");
        if (employee.getSurname() == null || employee.getSurname().isEmpty() || employee.getSurname().isBlank())
            throw new NullOrEmptyException("Employee's surname");
        if (employee.getRole() == null || employee.getRole().isEmpty() || employee.getRole().isBlank())
            throw new NullOrEmptyException("Employee's role");
        if (employee.getSalary() == null)
            throw new NullOrEmptyException("Employee's salary");
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(UUID id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        return employeeRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Employee")
        );
    }

    @Override
    public List<Employee> getAllByRole(String role) {
        if (role == null || role.isEmpty() || role.isBlank())
            throw new NullOrEmptyException("Role");
        List<Employee> allByRole = employeeRepository.findAllByRole(role);
        if (allByRole.isEmpty())
            throw new NotFoundException("Employees by role");
        return allByRole;
    }

    @Override
    public Employee getByEmail(String email) {
        if (email == null || email.isEmpty() || email.isBlank())
            throw new NullOrEmptyException("Role");
        return employeeRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("Employee")
        );
    }

    @Override
    public Employee getByName(String name) {
        if (name == null || name.isEmpty() || name.isBlank())
            throw new NullOrEmptyException("Name");
        return employeeRepository.findByName(name).orElseThrow(
                () -> new NotFoundException("Employee")
        );
    }

    @Override
    public Employee getBySurname(String surname) {
        if (surname == null || surname.isEmpty() || surname.isBlank())
            throw new NullOrEmptyException("Surname");
        return employeeRepository.findBySurname(surname).orElseThrow(
                () -> new NotFoundException("Employee")
        );
    }

    @Override
    public Employee getByPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty() || phoneNumber.isBlank())
            throw new NullOrEmptyException("Phone number");
        return employeeRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new NotFoundException("Employee")
        );
    }

    @Override
    public Employee getBySalary(Double salary) {
        if (salary == null)
            throw new NullOrEmptyException("Salary");
        return employeeRepository.findBySalary(salary).orElseThrow(
                () -> new NotFoundException("Employee")
        );
    }

    @Override
    public void update(Employee employee) {

    }

    @Override
    public void deleteAll() {
        if (getAll().isEmpty())
            throw new NotFoundException("Employees");
        employeeRepository.deleteAll();
    }

    @Override
    public void deleteById(UUID id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        if (getById(id) == null)
            throw new NotFoundException("Employee");
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteAllByRole(String role) {
        if (role == null || role.isBlank() || role.isEmpty())
            throw new NullOrEmptyException("Role");
        if (getAllByRole(role).isEmpty())
            throw new NotFoundException("Employees by role");
        employeeRepository.deleteAllByRole(role);
    }

    @Override
    public void deleteByEmail(String email) {
        if (email == null || email.isBlank() || email.isEmpty())
            throw new NullOrEmptyException("Email");
        if (getByEmail(email) == null)
            throw new NotFoundException("Employee with this email: " + email);
        employeeRepository.deleteByEmail(email);
    }

    @Override
    public void deleteByName(String name) {
        if (name == null || name.isBlank() || name.isEmpty())
            throw new NullOrEmptyException("Name");
        if (getByName(name) == null)
            throw new NotFoundException("Employee with this name: " + name);
        employeeRepository.deleteByName(name);
    }

    @Override
    public void deleteBySurname(String surname) {
        if (surname == null || surname.isBlank() || surname.isEmpty())
            throw new NullOrEmptyException("Surname");
        if (getBySurname(surname) == null)
            throw new NotFoundException("Employee with this surname: " + surname);
        employeeRepository.deleteBySurname(surname);
    }

    @Override
    public void deleteByPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank() || phoneNumber.isEmpty())
            throw new NullOrEmptyException("Phone number");
        if (getByPhoneNumber(phoneNumber) == null)
            throw new NotFoundException("Employee with this phone number: " + phoneNumber);
        employeeRepository.deleteByPhoneNumber(phoneNumber);
    }
}
