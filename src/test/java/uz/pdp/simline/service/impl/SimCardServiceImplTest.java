package uz.pdp.simline.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import uz.pdp.simline.dto.request.BuyNumberDto;
import uz.pdp.simline.dto.request.SimCardUpdateDto;
import uz.pdp.simline.dto.respone.SimCardDto;
import uz.pdp.simline.entity.Balance;
import uz.pdp.simline.entity.Plan;
import uz.pdp.simline.entity.SimCard;
import uz.pdp.simline.entity.User;
import uz.pdp.simline.repository.PlanRepository;
import uz.pdp.simline.repository.SimCardRepository;
import uz.pdp.simline.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SimCardServiceImplTest {

    @Mock
    private SimCardRepository simCardRepository;

    @Mock
    private PlanRepository planRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SimCardServiceImpl simCardService;

    @Test
    void testUpdateSimCard() {
        UUID simCardId = UUID.randomUUID();
        SimCardUpdateDto updateDto = new SimCardUpdateDto(simCardId, null, null);

        SimCard simCard = new SimCard();
        simCard.setId(simCardId);
        simCard.setNumber("1234567890");
        simCard.setPrice(10.0);
        simCard.setIsActive(true);
        when(simCardRepository.findById(simCardId)).thenReturn(Optional.of(simCard));
        when(simCardRepository.save(simCard)).thenReturn(simCard);

        SimCardDto updatedSimCardDto = simCardService.update(updateDto);
        assertNotNull(updatedSimCardDto);
        assertEquals(simCardId, updatedSimCardDto.getId());
    }

    @Test
    void testGetUnBookedSimCard() {
        String number = "1234567890";
        SimCard simCard = new SimCard();
        simCard.setNumber(number);
        when(simCardRepository.findByNumber(number)).thenReturn(Optional.of(simCard));

        SimCardDto simCardDto = simCardService.getUnBookedSimCard(number);
        assertNotNull(simCardDto);
        assertEquals(number, simCardDto.getNumber());
    }

    @Test
    void testGetById() {
        UUID simCardId = UUID.randomUUID();
        SimCard simCard = new SimCard();
        simCard.setId(simCardId);
        when(simCardRepository.findById(simCardId)).thenReturn(Optional.of(simCard));

        SimCardDto simCardDto = simCardService.getById(simCardId);
        assertNotNull(simCardDto);
        assertEquals(simCardId, simCardDto.getId());
    }

    @Test
    void testGetByNumber() {
        String number = "1234567890";
        SimCard simCard = new SimCard();
        simCard.setNumber(number);
        when(simCardRepository.findByNumber(number)).thenReturn(Optional.of(simCard));

        SimCardDto simCardDto = simCardService.getByNumber(number);
        assertNotNull(simCardDto);
        assertEquals(number, simCardDto.getNumber());
    }

    @Test
    void testGetByPrice() {
        double price = 20.0;
        SimCard simCard1 = new SimCard();
        simCard1.setId(UUID.randomUUID());
        simCard1.setPrice(price);
        SimCard simCard2 = new SimCard();
        simCard2.setId(UUID.randomUUID());
        simCard2.setPrice(price);

        List<SimCard> simCards = List.of(simCard1, simCard2);
        when(simCardRepository.findAllByPrice(price)).thenReturn(simCards);

        List<SimCardDto> simCardDtos = simCardService.getByPrice(price);
        assertEquals(2, simCardDtos.size());
        for (SimCardDto simCardDto : simCardDtos) {
            assertEquals(price, simCardDto.getPrice());
        }
    }

    @Test
    void testGetAllByWithPriceLessThan() {
        double price = 30.0;
        SimCard simCard1 = new SimCard();
        simCard1.setId(UUID.randomUUID());
        simCard1.setPrice(20.0);
        SimCard simCard2 = new SimCard();
        simCard2.setId(UUID.randomUUID());
        simCard2.setPrice(25.0);

        List<SimCard> simCards = List.of(simCard1, simCard2);
        when(simCardRepository.findAllByPriceLessThan(price)).thenReturn(simCards);

        List<SimCardDto> simCardDtos = simCardService.getAllByWithPriceLessThan(price);
        assertEquals(2, simCardDtos.size());
        for (SimCardDto simCardDto : simCardDtos) {
            assertTrue(simCardDto.getPrice() < price);
        }
    }

    @Test
    void testGetAllByWithPriceGreaterThan() {
        double price = 15.0;
        SimCard simCard1 = new SimCard();
        simCard1.setId(UUID.randomUUID());
        simCard1.setPrice(20.0);
        SimCard simCard2 = new SimCard();
        simCard2.setId(UUID.randomUUID());
        simCard2.setPrice(25.0);

        List<SimCard> simCards = List.of(simCard1, simCard2);
        when(simCardRepository.findAllByPriceGreaterThan(price)).thenReturn(simCards);

        List<SimCardDto> simCardDtos = simCardService.getAllByWithPriceGreaterThan(price);
        assertEquals(2, simCardDtos.size());
        for (SimCardDto simCardDto : simCardDtos) {
            assertTrue(simCardDto.getPrice() > price);
        }
    }

    @Test
    void testGetAllByActivity() {
        boolean isActive = true;
        SimCard simCard1 = new SimCard();
        simCard1.setId(UUID.randomUUID());
        simCard1.setIsActive(isActive);
        SimCard simCard2 = new SimCard();
        simCard2.setId(UUID.randomUUID());
        simCard2.setIsActive(isActive);
        List<SimCard> simCards = List.of(simCard1, simCard2);
        when(simCardRepository.findByIsActive(isActive)).thenReturn(simCards);

        List<SimCardDto> simCardDtos = simCardService.getAllByActivity(isActive);
        assertEquals(2, simCardDtos.size());
        for (SimCardDto simCardDto : simCardDtos) {
            assertTrue(simCardDto.getIsActive());
        }
    }

    @Test
    void testGetAllByPlanId() {
        UUID planId = UUID.randomUUID();
        SimCard simCard1 = new SimCard();
        simCard1.setId(UUID.randomUUID());
        Plan plan1 = new Plan();
        plan1.setId(planId);
        simCard1.setPlan(plan1);

        SimCard simCard2 = new SimCard();
        simCard2.setId(UUID.randomUUID());
        Plan plan2 = new Plan();
        plan2.setId(planId);
        simCard2.setPlan(plan2);

        List<SimCard> simCards = List.of(simCard1, simCard2);
        when(simCardRepository.findAllByPlanId(planId)).thenReturn(simCards);

        List<SimCardDto> simCardDtos = simCardService.getAllByPlanId(planId);
        assertEquals(2, simCardDtos.size());
        for (SimCardDto simCardDto : simCardDtos) {
            assertEquals(planId, simCardDto.getPlan().getId());
        }
    }


    @Test
    void testGetAll() {
        List<SimCard> simCards = new ArrayList<>();
        simCards.add(new SimCard());
        simCards.add(new SimCard());
        when(simCardRepository.findAll()).thenReturn(simCards);

        List<SimCardDto> simCardDtos = simCardService.getAll();
        assertEquals(2, simCardDtos.size());
    }

    @Test
    void testGetAllByBalance() {
        Balance balance = Balance.builder().balance(50.0).build();
        SimCard simCard1 = new SimCard();
        simCard1.setId(UUID.randomUUID());
        simCard1.setBalance(balance);
        SimCard simCard2 = new SimCard();
        simCard2.setId(UUID.randomUUID());
        simCard2.setBalance(balance);

        List<SimCard> simCards = List.of(simCard1, simCard2);
        when(simCardRepository.findAllByBalance(balance.getBalance())).thenReturn(simCards);

        List<SimCardDto> simCardDtos = simCardService.getAllByBalance(balance.getBalance());
        assertEquals(2, simCardDtos.size());
        for (SimCardDto simCardDto : simCardDtos) {
            assertEquals(balance, simCardDto.getBalance());
        }
    }

    @Test
    void testGetAllByWithBalanceLessThan() {
        double balance = 100.0;
        SimCard simCard1 = new SimCard();
        simCard1.setId(UUID.randomUUID());
        simCard1.setBalance(Balance.builder().balance(50.0).build());
        SimCard simCard2 = new SimCard();
        simCard2.setId(UUID.randomUUID());
        simCard2.setBalance(Balance.builder().balance(75.0).build());

        List<SimCard> simCards = List.of(simCard1, simCard2);
        when(simCardRepository.findAllByBalanceLessThan(balance)).thenReturn(simCards);

        List<SimCardDto> simCardDtos = simCardService.getAllByWithBalanceLessThan(balance);
        assertEquals(2, simCardDtos.size());
        for (SimCardDto simCardDto : simCardDtos) {
            assertTrue(simCardDto.getBalance().getBalance() < balance);
        }
    }


    @Test
    void testGetAllByWithBalanceGreaterThan() {
        double balance = 50.0;
        SimCard simCard1 = new SimCard();
        simCard1.setId(UUID.randomUUID());
        simCard1.setBalance(Balance.builder().balance(100.0).build());
        SimCard simCard2 = new SimCard();
        simCard2.setId(UUID.randomUUID());
        simCard2.setBalance(Balance.builder().balance(75.0).build());

        List<SimCard> simCards = List.of(simCard1, simCard2);
        when(simCardRepository.findAllByBalanceGreaterThan(balance)).thenReturn(simCards);

        List<SimCardDto> simCardDtos = simCardService.getAllByWithBalanceGreaterThan(balance);
        assertEquals(2, simCardDtos.size());
        for (SimCardDto simCardDto : simCardDtos) {
            assertTrue(simCardDto.getBalance().getBalance() > balance);
        }
    }

    @Test
    void testGetUnbookedNumbers() {
        SimCard simCard1 = new SimCard();
        simCard1.setId(UUID.randomUUID());
        simCard1.setIsActive(false);
        SimCard simCard2 = new SimCard();
        simCard2.setId(UUID.randomUUID());
        simCard2.setIsActive(false);
        List<SimCard> simCards = List.of(simCard1, simCard2);

        when(simCardRepository.findAll()).thenReturn(simCards);

        List<SimCardDto> unbookedSimCards = simCardService.getUnbookedNumbers();
        assertEquals(2, unbookedSimCards.size());
        for (SimCardDto simCardDto : unbookedSimCards) {
            assertFalse(simCardDto.getIsActive());
        }
    }

    @Test
    void testBuyByNumber() {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setBalance(100.0);

        SimCard simCard = new SimCard();
        simCard.setId(UUID.randomUUID());
        simCard.setPrice(50.0);

        BuyNumberDto buyNumberDto = new BuyNumberDto(user.getId(),simCard.getId());

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(simCardRepository.findById(simCard.getId())).thenReturn(Optional.of(simCard));
        when(userRepository.findBySimCard(simCard.getNumber())).thenReturn(Optional.empty());

        simCardService.buyByNumber(buyNumberDto);

        assertEquals(50.0, user.getBalance());
        assertTrue(user.getSimCards().contains(simCard));
        assertTrue(simCard.getIsActive());
    }

    @Test
    void testGetBalanceByNumber() {
        String number = "1234567";
        Balance balance = new Balance();
        balance.setBalance(50.0);

        when(simCardRepository.findBalanceByNumber(number)).thenReturn(Optional.of(balance));

        Balance retrievedBalance = simCardService.getBalanceByNumber(number);
        assertNotNull(retrievedBalance);
        assertEquals(50.0, retrievedBalance.getBalance());
    }
}
