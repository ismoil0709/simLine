package uz.pdp.simline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.simline.entity.Plan;
import uz.pdp.simline.entity.SimCard;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SimCardRepository extends JpaRepository<SimCard, UUID> {
    Optional<SimCard> findByNumber(String number);
    @Query("SELECT s FROM SimCard s WHERE s.price BETWEEN ? AND ?")
    List<SimCard> findSimCardByPriceBetweenMaxPriceAndMinPrice(Double minPrice, Double maxPrice);

    Optional<SimCard> findByIsActive(Boolean isActive);

    List<SimCard> findAllByPlan(Plan plan);
    List<SimCard> findAllByBalance(Double balance);
    @Query("SELECT s FROM SimCard s WHERE s.balance BETWEEN ? AND ?")
    List<SimCard> findSimCardsByBalanceBetweenMinBalanceAndMaxBalance(Double minPrice, Double maxPrice);
    @Query("SELECT s.balance FROM SimCard s WHERE s.number = ?")
    Optional<Double> findBalanceByNumber(String number);
}