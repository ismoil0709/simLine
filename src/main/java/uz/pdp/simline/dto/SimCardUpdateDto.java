package uz.pdp.simline.dto;

import uz.pdp.simline.entity.Plan;

import java.util.UUID;

public record SimCardUpdateDto (UUID id,Double price, Boolean isActive, Plan plan) {
}
