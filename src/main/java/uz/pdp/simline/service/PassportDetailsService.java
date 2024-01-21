package uz.pdp.simline.service;

import org.springframework.stereotype.Service;
import uz.pdp.simline.entity.PassportDetail;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface PassportDetailsService {
    List<PassportDetail> getPassposrtDetalis();

    Optional<PassportDetail> getPassportDetailById(UUID id);

    void savePassportDetail(PassportDetail passportDetail);

    void updatePassportDetail(UUID id, PassportDetail updatedDetails);

    void deletePassportDetail(UUID id);
}
