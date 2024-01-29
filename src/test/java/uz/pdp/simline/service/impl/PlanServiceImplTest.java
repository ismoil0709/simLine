package uz.pdp.simline.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uz.pdp.simline.dto.respone.PlanDto;
import uz.pdp.simline.entity.Balance;
import uz.pdp.simline.entity.Plan;
import uz.pdp.simline.entity.SimCard;
import uz.pdp.simline.exception.AlreadyExistsException;
import uz.pdp.simline.exception.NotFoundException;
import uz.pdp.simline.exception.NullOrEmptyException;
import uz.pdp.simline.repository.PlanRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlanServiceImplTest {

    @Mock
    private PlanRepository planRepository;

    @InjectMocks
    private PlanServiceImpl planService;

    private PlanDto testPlanDto;
    private Plan testPlan;

    @BeforeEach
    void setUp() {
        testPlanDto = new PlanDto(UUID.randomUUID(), "Test Plan", 30L, 1024L, 100L, 100L, 25.0);
        testPlan = PlanDto.castToPlan(testPlanDto);
        SimCard testSimCard = new SimCard();
        testSimCard.setBalance(Balance.builder().balance(30.0).build());
    }

    @Test
    void save_ValidPlanDto_ShouldReturnSavedPlanDto() {
        when(planRepository.findByName(testPlanDto.getName())).thenReturn(Optional.empty());
        when(planRepository.save(any())).thenReturn(testPlan);

        PlanDto savedPlanDto = planService.save(testPlanDto);

        assertNotNull(savedPlanDto);
        assertEquals(testPlanDto, savedPlanDto);
        verify(planRepository, times(1)).findByName(testPlanDto.getName());
        verify(planRepository, times(1)).save(any());
    }

    @Test
    void save_PlanDtoWithExistingName_ShouldThrowAlreadyExistsException() {
        when(planRepository.findByName(testPlanDto.getName())).thenReturn(Optional.of(testPlan));

        assertThrows(AlreadyExistsException.class, () -> planService.save(testPlanDto));

        verify(planRepository, times(1)).findByName(testPlanDto.getName());
        verify(planRepository, never()).save(any());
    }

    @Test
    void delete_ExistingPlanId_ShouldDeletePlan() {
        UUID testPlanId = UUID.randomUUID();
        when(planRepository.findById(testPlanId)).thenReturn(Optional.of(testPlan));

        assertDoesNotThrow(() -> planService.delete(testPlanId));

        verify(planRepository, times(1)).findById(testPlanId);
        verify(planRepository, times(1)).delete(testPlan);
    }

    @Test
    void delete_NonExistingPlanId_ShouldThrowNotFoundException() {
        UUID nonExistingPlanId = UUID.randomUUID();
        when(planRepository.findById(nonExistingPlanId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> planService.delete(nonExistingPlanId));

        verify(planRepository, times(1)).findById(nonExistingPlanId);
        verify(planRepository, never()).delete(any());
    }

    @Test
    void deleteByName_ExistingPlanName_ShouldDeletePlan() {
        String testPlanName = "Test Plan";
        when(planRepository.findByName(testPlanName)).thenReturn(Optional.of(testPlan));

        assertDoesNotThrow(() -> planService.deleteByName(testPlanName));

        verify(planRepository, times(1)).findByName(testPlanName);
        verify(planRepository, times(1)).delete(testPlan);
    }

    @Test
    void deleteByName_NonExistingPlanName_ShouldThrowNotFoundException() {
        String nonExistingPlanName = "Non Existing Plan";
        when(planRepository.findByName(nonExistingPlanName)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> planService.deleteByName(nonExistingPlanName));

        verify(planRepository, times(1)).findByName(nonExistingPlanName);
        verify(planRepository, never()).delete(any());
    }

    @Test
    void getAll_PlansExist_ShouldReturnListOfPlanDtos() {
        List<Plan> plans = Collections.singletonList(testPlan);
        when(planRepository.findAll()).thenReturn(plans);

        List<PlanDto> allPlanDtos = planService.getAll();

        assertFalse(allPlanDtos.isEmpty());
        assertEquals(plans.size(), allPlanDtos.size());
        assertTrue(allPlanDtos.contains(testPlanDto));
        verify(planRepository, times(1)).findAll();
    }

    @Test
    void getAll_NoPlansExist_ShouldThrowNullOrEmptyException() {
        when(planRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(NullOrEmptyException.class, () -> planService.getAll());

        verify(planRepository, times(1)).findAll();
    }
}
