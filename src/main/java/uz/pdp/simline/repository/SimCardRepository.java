package uz.pdp.simline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.simline.entity.Balance;
import uz.pdp.simline.entity.Plan;
import uz.pdp.simline.entity.SimCard;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SimCardRepository extends JpaRepository<SimCard, UUID> {
    Optional<SimCard> findByNumber(String number);

    List<SimCard> findAllByPriceLessThan(Double price);

    List<SimCard> findAllByPriceGreaterThan(Double price);
    List<SimCard> findAllByPrice(Double price);

    List<SimCard> findByIsActive(Boolean isActive);

    @Query("SELECT s FROM SimCard s WHERE s.plan.id=?1")
    List<SimCard> findAllByPlanId(UUID planId);

    @Query("SELECT s FROM SimCard s WHERE s.balance.balance=?1")
    List<SimCard> findAllByBalance(Double balance);
    @Query("SELECT s FROM SimCard s WHERE s.balance.balance > ?1")
    List<SimCard> findAllByBalanceGreaterThan(Double balance);
    @Query("SELECT s FROM SimCard s WHERE s.balance.balance < ?1")
    List<SimCard> findAllByBalanceLessThan(Double balance);
    @Query("SELECT s.balance FROM SimCard s WHERE s.number = ?1")
    Optional<Balance> findBalanceByNumber(String number);
}