package uz.pdp.simline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.simline.entity.SimCard;

import java.util.UUID;

@Repository
public interface SimCardRepository extends JpaRepository<SimCard, UUID> {
}