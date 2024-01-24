package uz.pdp.simline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.simline.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByEmail(String email);
    List<User> findAllByGender(String gender);
    List<User> findAllByAddress(String address);
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.role=?1")
    List<User> findAllByRole(String role);
    @Query("SELECT u FROM User u JOIN u.simCards s WHERE s.number=?1")
    Optional<User> findBySimCard(String number);
    @Query("SELECT u FROM User u WHERE u.passportDetail.passportId=?1")
    Optional<User> findByPassportId(String passportId);
}