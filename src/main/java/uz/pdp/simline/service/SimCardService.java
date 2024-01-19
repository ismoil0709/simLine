package uz.pdp.simline.service;

import org.springframework.stereotype.Service;
import uz.pdp.simline.dto.SimCardUpdateDto;
import uz.pdp.simline.entity.SimCard;

import java.util.UUID;

@Service
public interface SimCardService {
    SimCard create(SimCard simCard);
    SimCard getById(UUID id);
    SimCard getByNumber(String number);
    SimCard getByPrice(Double minPrice, Double maxPrice);
    SimCard getByActivity(Boolean isActive);
    void update(SimCardUpdateDto simCardUpdateDto);
    void deleteById(UUID id);
    void deleteByNumber(String number);
    void deleteByActivity(Boolean isActive);

}
