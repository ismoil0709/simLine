package uz.pdp.simline.service;

import uz.pdp.simline.dto.request.EmployeeCreationDto;
import uz.pdp.simline.dto.request.EmployeeUpdateDto;
import uz.pdp.simline.dto.respone.EmployeeDto;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    EmployeeDto save(EmployeeCreationDto employeeDto);
    List<EmployeeDto> getAll();
    EmployeeDto getById(UUID id);
    List<EmployeeDto> getAllByRole(String role);
    EmployeeDto getByEmail(String email);
    EmployeeDto getByName(String name);
    EmployeeDto getBySurname(String surname);
    EmployeeDto getByPhoneNumber(String phoneNumber);
    EmployeeDto getBySalary(Double salary);
    EmployeeDto update(EmployeeUpdateDto employeeDto);
}
