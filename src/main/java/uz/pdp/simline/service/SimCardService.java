package uz.pdp.simline.service;

import org.springframework.stereotype.Service;
import uz.pdp.simline.dto.request.SimCardUpdateDto;
import uz.pdp.simline.entity.Plan;
import uz.pdp.simline.entity.SimCard;

import java.util.List;
import java.util.UUID;

@Service
public interface SimCardService {
    SimCard getById(UUID id);
    SimCard getByNumber(String number);
    List<SimCard> getByPrice(Double minPrice, Double maxPrice);
    SimCard getByActivity(Boolean isActive);
    void update(SimCardUpdateDto simCardUpdateDto);
    List<SimCard> getAllByPlan(Plan plan);
    List<SimCard> getAll();
    List<SimCard> getAllByBalance(Double balance);
    List<SimCard> getSimCardsBetweenMinBalanceAndMaxBalance(Double minBalance, Double maxBalance);

    Double getBalanceByNumber(String number);

}
