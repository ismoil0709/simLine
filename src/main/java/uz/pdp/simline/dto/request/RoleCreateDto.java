package uz.pdp.simline.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.simline.util.annotations.Role;

@AllArgsConstructor
@Getter
public class RoleCreateDto {
    @Role
    private String role;
    private String description;
}
