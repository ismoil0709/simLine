package uz.pdp.simline.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.simline.util.annotations.Role;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class RoleDto {
    @Role
    private String role;
    private UUID userId;
}
