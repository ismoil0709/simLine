package uz.pdp.simline.service;

import org.springframework.stereotype.Service;
import uz.pdp.simline.dto.request.BuyNumberDto;
import uz.pdp.simline.dto.request.SimCardUpdateDto;
import uz.pdp.simline.dto.respone.SimCardDto;
import uz.pdp.simline.entity.Balance;
import uz.pdp.simline.entity.Plan;
import uz.pdp.simline.entity.SimCard;

import java.util.List;
import java.util.UUID;

@Service
public interface SimCardService {
    SimCardDto update(SimCardUpdateDto simCardUpdateDto);
    SimCardDto getUnBookedByNumber(String number);
    SimCardDto getById(UUID id);
    List<SimCardDto> getAll();
    SimCardDto getByNumber(String number);
    List<SimCardDto> getByPrice(Double price);
    List<SimCardDto> getAllByWithPriceLessThan(Double price);
    List<SimCardDto> getAllByWithPriceGreaterThan(Double price);
    List<SimCardDto> getUnbookedByPrice(Double price);
    List<SimCardDto> getUnbookedWithPriceLessThan(Double price);
    List<SimCardDto> getUnbookedWithPriceGreaterThan(Double price);
    List<SimCardDto> getAllByActivity(Boolean isActive);
    List<SimCardDto> getAllByPlanId(UUID planId);
    Balance getBalanceByNumber(String number);
    List<SimCardDto> getAllByBalance(Double balance);
    List<SimCardDto> getAllByWithBalanceLessThan(Double price);
    List<SimCardDto> getAllByWithBalanceGreaterThan(Double price);
    List<SimCardDto> getUnbookedNumbers();
    List<SimCardDto> getAllByCustomNumber(String number);
    void buyByNumber(BuyNumberDto buyNumberDto);
}
