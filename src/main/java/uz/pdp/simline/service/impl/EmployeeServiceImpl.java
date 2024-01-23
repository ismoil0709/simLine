package uz.pdp.simline.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.simline.dto.request.EmployeeCreationDto;
import uz.pdp.simline.dto.request.EmployeeUpdateDto;
import uz.pdp.simline.dto.respone.EmployeeDto;
import uz.pdp.simline.dto.respone.JwtDto;
import uz.pdp.simline.entity.Employee;
import uz.pdp.simline.entity.Role;
import uz.pdp.simline.exception.AlreadyExistsException;
import uz.pdp.simline.exception.InvalidArgumentException;
import uz.pdp.simline.exception.NotFoundException;
import uz.pdp.simline.exception.NullOrEmptyException;
import uz.pdp.simline.repository.EmployeeRepository;
import uz.pdp.simline.repository.RoleRepository;
import uz.pdp.simline.security.jwt.JwtTokenProvider;
import uz.pdp.simline.service.EmployeeService;
import uz.pdp.simline.util.Validations;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtDto save(EmployeeCreationDto employeeDto) {
        if (employeeDto == null)
            throw new NullOrEmptyException("Employee");
        if (employeeDto.getPassportDetail() == null)
            throw new NullOrEmptyException("Passport Details");
        if (Validations.isNullOrEmpty(employeeDto.getEmail()))
            throw new NullOrEmptyException("Email");
        if (Validations.isNullOrEmpty(employeeDto.getPhoneNumber()))
            throw new NullOrEmptyException("Phone Number");
        if (Validations.isNullOrEmpty(employeeDto.getGender()) &&
                !employeeDto.getGender().equals("MALE") || !employeeDto.getGender().equals("FEMALE"))
            throw new NullOrEmptyException("Gender");
        if (Validations.isNullOrEmpty(employeeDto.getPassword()))
            throw new NullOrEmptyException("Password");
        if (Validations.isNullOrEmpty(employeeDto.getAddress()))
            throw new NullOrEmptyException("Address");
        if (Validations.isNullOrEmpty(employeeDto.getPassportDetail().getName()))
            throw new NullOrEmptyException("Passport name");
        if (Validations.isNullOrEmpty(employeeDto.getPassportDetail().getSurname()))
            throw new NullOrEmptyException("Passport surname");
        if (employeeDto.getPassportDetail().getBirthDate() == null)
            throw new NullOrEmptyException("Passport birth date");
        if (employeeDto.getPassportDetail().getPassportId() == null)
            throw new NullOrEmptyException("PassportId");
        if (employeeRepository.findByEmail(employeeDto.getEmail()).isPresent())
            throw new AlreadyExistsException("Email");
        if (employeeRepository.findByPhoneNumber(employeeDto.getPhoneNumber()).isPresent())
            throw new AlreadyExistsException("Phone number");

        return new JwtDto(jwtTokenProvider.generateForEmployee(employeeRepository.save(
                Employee.builder()
                        .phoneNumber(employeeDto.getPhoneNumber())
                        .email(employeeDto.getEmail())
                        .password(passwordEncoder.encode(employeeDto.getPassword()))
                        .passportDetail(employeeDto.getPassportDetail())
                        .address(employeeDto.getAddress())
                        .build()
        )));
    }

    @Override
    public JwtDto login(String email, String password) {
        if (Validations.isNullOrEmpty(email))
            throw new NullOrEmptyException("Email");
        if (Validations.isNullOrEmpty(password))
            throw new NullOrEmptyException("Password");
        Employee employee = employeeRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Employee"));
        if (passwordEncoder.matches(password,employee.getPassword())){
            return new JwtDto(jwtTokenProvider.generateForEmployee(employee));
        }
        throw new InvalidArgumentException("password");
    }

    @Override
    public List<EmployeeDto> getAll() {
        List<Employee> all = employeeRepository.findAll();
        if (all.isEmpty())
            throw new NotFoundException("Employees");
        return all.stream().map(EmployeeDto::new).toList();
    }

    @Override
    public EmployeeDto getById(UUID id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        return new EmployeeDto(employeeRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Employee")
        ));
    }

    @Override
    public List<EmployeeDto> getAllByRole(String role) {
        if (Validations.isNullOrEmpty(role))
            throw new NullOrEmptyException("Role");
        List<Employee> allByRole = employeeRepository.findAllByRole(role);
        if (allByRole.isEmpty())
            throw new NotFoundException("Employees by role");
        return allByRole.stream().map(EmployeeDto::new).toList();
    }

    @Override
    public EmployeeDto getByEmail(String email) {
        if (Validations.isNullOrEmpty(email))
            throw new NullOrEmptyException("Role");
        return new EmployeeDto(employeeRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("Employee")
        ));
    }

    @Override
    public EmployeeDto getByName(String name) {
        if (Validations.isNullOrEmpty(name))
            throw new NullOrEmptyException("Name");
        return new EmployeeDto(employeeRepository.findByName(name).orElseThrow(
                () -> new NotFoundException("Employee")
        ));
    }

    @Override
    public EmployeeDto getBySurname(String surname) {
        if (Validations.isNullOrEmpty(surname))
            throw new NullOrEmptyException("Surname");
        return new EmployeeDto(employeeRepository.findBySurname(surname).orElseThrow(
                () -> new NotFoundException("Employee")
        ));
    }

    @Override
    public EmployeeDto getByPhoneNumber(String phoneNumber) {
        if (Validations.isNullOrEmpty(phoneNumber))
            throw new NullOrEmptyException("Phone number");
        return new EmployeeDto(employeeRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new NotFoundException("Employee")
        ));
    }

    @Override
    public EmployeeDto getBySalary(Double salary) {
        if (salary == null)
            throw new NullOrEmptyException("Salary");
        return new EmployeeDto(employeeRepository.findBySalary(salary).orElseThrow(
                () -> new NotFoundException("Employee")
        ));
    }

    @Override
    public EmployeeDto update(EmployeeUpdateDto employeeUpdateDto) {
        if (employeeUpdateDto == null)
            throw new NullOrEmptyException("Employee");
        if (employeeUpdateDto.getId() == null)
            throw new NullOrEmptyException("Id");
        Employee employee = employeeRepository.findById(employeeUpdateDto.getId()).orElseThrow(
                () -> new NotFoundException("Employee")
        );
        if (employeeUpdateDto.getPhoneNumber() != null
                && employeeRepository.findByPhoneNumber(employeeUpdateDto.getPhoneNumber()).isPresent()
        )
            throw new AlreadyExistsException("PhoneNumber");
        if (employeeUpdateDto.getEmail() != null
                && employeeRepository.findByPhoneNumber(employeeUpdateDto.getEmail()).isPresent()
        )
            throw new AlreadyExistsException("Email");
        if (employeeUpdateDto.getGender() != null
                && !employeeUpdateDto.getGender().equals("MALE") ||
                !Objects.equals(employeeUpdateDto.getGender(), "FEMALE")
        )
            throw new NullOrEmptyException("Gender");
        return new EmployeeDto(
                employeeRepository.save(
                        Employee.builder()
                                .id(employeeUpdateDto.getId())
                                .address(Validations.requireNonNullElse(employeeUpdateDto.getAddress(),employee.getAddress()))
                                .salary(Validations.requireNonNullElse(employeeUpdateDto.getSalary(),employee.getSalary()))
                                .active(employeeUpdateDto.isActive())
                                .position(Validations.requireNonNullElse(employeeUpdateDto.getPosition(),employee.getPosition()))
                                .email(Validations.requireNonNullElse(employeeUpdateDto.getEmail(),employee.getEmail()))
                                .phoneNumber(Validations.requireNonNullElse(employeeUpdateDto.getPhoneNumber(),employee.getPhoneNumber()))
                                .passportDetail(Validations.requireNonNullElse(employeeUpdateDto.getPassportDetail(),employee.getPassportDetail()))
                                .employeeRoles(
                                        Validations.requireNonNullElse(
                                                employeeUpdateDto.getEmployeeRoles_id().stream().map(
                                                        id -> roleRepository.findById(id).orElseThrow(()->new NotFoundException("Role"))
                                                ).toList(),
                                                employee.getEmployeeRoles()
                                        )
                                )
                                .build()
                )
        );
    }
}
