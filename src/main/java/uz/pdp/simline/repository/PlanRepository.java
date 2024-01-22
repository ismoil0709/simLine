package uz.pdp.simline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.simline.entity.Plan;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlanRepository extends JpaRepository<Plan, UUID> {
    Optional<Plan> findByName(String name);

    Optional<Plan> findByPrice(Double price);

    List<Plan> findByPriceLessThan(Double maxPrice);
}
