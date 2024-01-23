package uz.pdp.simline.service;

import org.springframework.stereotype.Service;
import uz.pdp.simline.dto.request.SimCardUpdateDto;
import uz.pdp.simline.dto.respone.SimCardDto;
import uz.pdp.simline.entity.Plan;
import uz.pdp.simline.entity.SimCard;

import java.util.List;
import java.util.UUID;

@Service
public interface SimCardService {
    SimCardDto getById(UUID id);
    SimCardDto getByNumber(String number);
    List<SimCardDto> getByPrice(Double minPrice, Double maxPrice);
    SimCardDto getByActivity(Boolean isActive);
    void update(SimCardUpdateDto simCardUpdateDto);
    List<SimCardDto> getAllByPlan(Plan plan);
    List<SimCardDto> getAll();
    Double getBalanceByNumber(String number);
    List<SimCardDto> getAllByBalance(Double balance);
    List<SimCard> getSimCardsBetweenMinBalanceAndMaxBalance(Double minBalance, Double maxBalance);
}
