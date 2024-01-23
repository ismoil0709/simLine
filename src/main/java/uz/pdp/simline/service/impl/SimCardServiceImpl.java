package uz.pdp.simline.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.simline.dto.request.SimCardUpdateDto;
import uz.pdp.simline.dto.respone.SimCardDto;
import uz.pdp.simline.entity.Plan;
import uz.pdp.simline.entity.SimCard;
import uz.pdp.simline.exception.NotFoundException;
import uz.pdp.simline.exception.NullOrEmptyException;
import uz.pdp.simline.repository.SimCardRepository;
import uz.pdp.simline.service.SimCardService;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SimCardServiceImpl implements SimCardService {
    private final SimCardRepository simCardRepository;


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
    public SimCardDto getByPrice(Double minPrice, Double maxPrice) {
        if (minPrice == null || maxPrice == null)
            throw new NullOrEmptyException("Price");
        return new SimCardDto(simCardRepository.findSimCardByPriceBetweenMaxPriceAndMinPrice
                (minPrice, maxPrice).orElseThrow(
                () -> new NotFoundException("SimCard")));
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
        SimCard simCard = simCardRepository.findById(simCardUpdateDto.id()).orElseThrow(
                () -> new NotFoundException("SimCard")
        );
        simCardRepository.save(
                SimCard.builder()
                        .id(simCardUpdateDto.id())
                        .isActive(Objects.requireNonNullElse(simCardUpdateDto.isActive(),simCard.getIsActive()))
                        .price(Objects.requireNonNullElse(simCardUpdateDto.price(),simCard.getPrice()))
                        //todo add plan get by id
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
}