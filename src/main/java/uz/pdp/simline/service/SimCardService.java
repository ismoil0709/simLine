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
    List<SimCardDto> getByActivity(Boolean isActive);
    SimCardDto update(SimCardUpdateDto simCardUpdateDto);
    List<SimCardDto> getAllByPlanId(UUID planId);
    List<SimCardDto> getAll();
    /**
     * Methods connected with Balance entity will be used after complete Balance entity
     * */
    Double getBalanceByNumber(String number);
    List<SimCardDto> getAllByBalance(Double balance);
    List<SimCard> getSimCardsBetweenMinBalanceAndMaxBalance(Double minBalance, Double maxBalance);
}
