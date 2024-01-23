package uz.pdp.simline.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import uz.pdp.simline.entity.Employee;
import uz.pdp.simline.entity.PassportDetail;
import uz.pdp.simline.entity.Role;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@Getter
public class EmployeeDto{
    private UUID id;
    private String phoneNumber;
    private String email;
    private String gender;
    private String address;
    private Double salary;
    private boolean active;
    private PassportDetailDto passportDetail;
    private List<Role> employeeRoles;
    private String position;
    public EmployeeDto(Employee employee){
        this.id = employee.getId();
        this.phoneNumber = employee.getPhoneNumber();
        this.email = employee.getEmail();
        this.gender = employee.getGender();
        this.address = employee.getAddress();
        this.salary = employee.getSalary();
        this.active = employee.isActive();
        this.passportDetail = new PassportDetailDto(
                employee.getPassportDetail().getId(),
                employee.getPassportDetail().getName(),
                employee.getPassportDetail().getSurname(),
                employee.getPassportDetail().getBirthDate(),
                employee.getPassportDetail().getPassportId()

        );
        this.employeeRoles = employee.getEmployeeRoles();
        this.position = employee.getPosition();
    }
}