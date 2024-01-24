package uz.pdp.simline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.simline.entity.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query("SELECT e FROM Employee e JOIN e.employeeRoles r WHERE r.role=?1")
    List<Employee> findAllByRole(String role);
    Optional<Employee> findByPhoneNumber(String phoneNumber);
    Optional<Employee> findByEmail(String email);
    @Query("SELECT e FROM Employee e WHERE e.passportDetail.name=?1")
    Optional<Employee> findByName(String name);
    @Query("SELECT e FROM Employee e WHERE e.passportDetail.surname=?1")
    Optional<Employee> findBySurname(String surname);
    Optional<Employee> findBySalary(Double salary);
    Optional<Employee> findByUsername(String username);
    }