package uz.pdp.simline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.simline.entity.PassportDetail;

import java.util.List;
import java.util.UUID;

@Repository
public interface PassportDetailRepository extends JpaRepository<PassportDetail, UUID> {
    List<PassportDetail> getPassportDetailsBy(UUID uuid);
}