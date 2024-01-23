package uz.pdp.simline.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.simline.dto.request.SimCardUpdateDto;
import uz.pdp.simline.dto.respone.SimCardDto;
import uz.pdp.simline.entity.Plan;
import uz.pdp.simline.entity.SimCard;
import uz.pdp.simline.exception.NotFoundException;
import uz.pdp.simline.exception.NullOrEmptyException;
import uz.pdp.simline.repository.PlanRepository;
import uz.pdp.simline.repository.SimCardRepository;
import uz.pdp.simline.service.SimCardService;
import uz.pdp.simline.util.Validations;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SimCardServiceImpl implements SimCardService {
    private final SimCardRepository simCardRepository;
    private final PlanRepository planRepository;
    @Override
    public SimCardDto getById(UUID id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        return new SimCardDto(simCardRepository.findById(id).orElseThrow(
                () -> new NotFoundException("SimCard")));
    }

    @Override
    public SimCardDto getByNumber(String number) {
        if (number == null || number.isEmpty() || number.isBlank())
            throw new NullOrEmptyException("Number");
        return new SimCardDto(simCardRepository.findByNumber(number).orElseThrow(
                () -> new NotFoundException("SimCard")));
    }

    @Override
    public List<SimCardDto> getByPrice(Double minPrice, Double maxPrice) {
        if (minPrice == null || maxPrice == null)
            throw new NullOrEmptyException("Price");
        List<SimCard> byPrice = simCardRepository.findSimCardByPriceBetweenMaxPriceAndMinPrice(minPrice, maxPrice);
        if (byPrice == null || byPrice.isEmpty())
            throw new NotFoundException("SimCards ");
        return byPrice.stream().map(SimCardDto::new).toList();
    }

    @Override
    public SimCardDto getByActivity(Boolean isActive) {
        if (isActive == null)
            throw new NullOrEmptyException("isActive");
        return new SimCardDto(simCardRepository.findByIsActive(isActive).orElseThrow(
                () -> new NotFoundException("SimCard")));
    }

    @Override
    public void update(SimCardUpdateDto simCardUpdateDto) {
        if (simCardUpdateDto == null)
            throw new NullOrEmptyException("SimCardUpdateDto");
        if (simCardUpdateDto.getId() == null)
            throw new NullOrEmptyException("Id");
        SimCard simCard = simCardRepository.findById(simCardUpdateDto.getId()).orElseThrow(
                () -> new NotFoundException("SimCard")
        );
        simCardRepository.save(
                SimCard.builder()
                        .id(simCardUpdateDto.getId())
                        .isActive(Objects.requireNonNullElse(simCardUpdateDto.getIsActive(),simCard.getIsActive()))
                        .price(Objects.requireNonNullElse(simCardUpdateDto.getPrice(),simCard.getPrice()))
                        .plan(planRepository.findById(
                                simCardUpdateDto.getPlanId() == null ? simCard.getPlan().getId() : simCardUpdateDto.getPlanId()
                        ).orElseThrow(
                                ()->new NotFoundException("Plan")
                        ))
                        .build()
        );
    }


    @Override
    public List<SimCardDto> getAllByPlan(Plan plan) {
        if (plan == null)
            throw new NullOrEmptyException("Plan");
        List<SimCard> allByPlan = simCardRepository.findAllByPlan(plan);
        if (allByPlan.isEmpty())
            throw new NullOrEmptyException("SimCards");
        return allByPlan.stream().map(SimCardDto::new).toList();
    }

    @Override
    public List<SimCardDto> getAll() {
        List<SimCard> all = simCardRepository.findAll();
        if (all.isEmpty())
            throw new NullOrEmptyException("SimCards");
        return all.stream().map(SimCardDto::new).toList();
    }

    @Override
    public List<SimCardDto> getAllByBalance(Double balance) {
        if (balance == null)
            throw new NullOrEmptyException("Balance");
        List<SimCard> allByBalance = simCardRepository.findAllByBalance(balance);
        if (allByBalance.isEmpty())
            throw new NullOrEmptyException("SimCards");
        return allByBalance.stream().map(SimCardDto::new).toList();
    }

    @Override
    public List<SimCard> getSimCardsBetweenMinBalanceAndMaxBalance(Double minBalance, Double maxBalance) {
        if (minBalance == null || maxBalance == null)
            throw new NullOrEmptyException("Balance ");
        List<SimCard> byMinAndMaxBalance = simCardRepository.findSimCardsByBalanceBetweenMinBalanceAndMaxBalance(minBalance, maxBalance);
        if (byMinAndMaxBalance == null || byMinAndMaxBalance.isEmpty())
            throw new NotFoundException("SimCards");
        return byMinAndMaxBalance;
    }

    @Override
    public Double getBalanceByNumber(String number) {
        if (Validations.isNullOrEmpty(number))
            throw new NullOrEmptyException("Number");
        return simCardRepository.findBalanceByNumber(number).orElseThrow(
                () -> new NotFoundException("Balance")
        );
    }
}