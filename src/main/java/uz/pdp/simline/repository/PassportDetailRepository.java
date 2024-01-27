package uz.pdp.simline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.simline.entity.PassportDetail;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PassportDetailRepository extends JpaRepository<PassportDetail, UUID> {
    Optional<PassportDetail> findByPassportId(String passportId);
    List<PassportDetail> findAllBySurname(String surname);
    List<PassportDetail> findAllByName(String name);
    List<PassportDetail> findAllByBirthDate(LocalDate birthDate);
    @Query("SELECT u.passportDetail FROM User u WHERE u.id = ?1")
    Optional<PassportDetail> findByUserId(UUID userId);
}