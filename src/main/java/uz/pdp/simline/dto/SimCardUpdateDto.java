package uz.pdp.simline.dto;

import uz.pdp.simline.entity.Plan;

public record SimCardUpdateDto (Double price, Boolean isActive, Plan plan) {
}
