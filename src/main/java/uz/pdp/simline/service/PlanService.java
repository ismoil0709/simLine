package uz.pdp.simline.service;

import org.springframework.stereotype.Service;
import uz.pdp.simline.dto.request.BuyPlanDto;
import uz.pdp.simline.dto.respone.PlanDto;

import java.util.List;
import java.util.UUID;

@Service
public interface PlanService {
    PlanDto save(PlanDto plan);
    PlanDto update(PlanDto updatedPlan);
    void delete(UUID id);
    void deleteByName(String name);
    PlanDto getById(UUID id);
    List<PlanDto> getAll();
    PlanDto getByName(String name);
    PlanDto getByPrice(Double price);
    List<PlanDto> getByWithPriceLessThan(Double maxPrice);
    List<PlanDto> getByWithPriceGreaterThan(Double minPrice);
    List<PlanDto > getByMb(Long mb);
    List<PlanDto> getBySms(Long sms);
    List<PlanDto> getByMinute(Long minute);
    void buyPlan(BuyPlanDto buyPlanDto);
}
