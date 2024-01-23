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
    @Query("SELECT s FROM SimCard s WHERE s.price BETWEEN ?1 AND ?2")
    List<SimCard> findSimCardByPriceBetweenMaxPriceAndMinPrice(Double minPrice, Double maxPrice);
    List<SimCard> findByIsActive(Boolean isActive);
    List<SimCard> findAllByPlanId(UUID planId);
    @Query("SELECT s FROM SimCard s WHERE s.balance.balance=?1")
    List<SimCard> findAllByBalance(Double balance);
    @Query("SELECT s FROM SimCard s WHERE s.balance.balance BETWEEN ?1 AND ?2")
    List<SimCard> findSimCardsByBalanceBetweenMinBalanceAndMaxBalance(Double minPrice, Double maxPrice);
    @Query("SELECT s.balance FROM SimCard s WHERE s.number = ?1")
    Optional<Double> findBalanceByNumber(String number);
}