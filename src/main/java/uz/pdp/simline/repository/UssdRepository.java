package uz.pdp.simline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.simline.entity.Ussd;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UssdRepository extends JpaRepository<Ussd, UUID> {
    Optional<Ussd> findByCode(String code);
}