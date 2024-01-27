package uz.pdp.simline.service;

import org.springframework.stereotype.Service;
import uz.pdp.simline.dto.request.RoleCreateDto;
import uz.pdp.simline.dto.request.RoleDto;
import uz.pdp.simline.entity.Role;

import java.util.List;
import java.util.UUID;

@Service
public interface RoleService {
    void addRoleToUser(RoleDto roleDto);
    void deleteRoleFromUser(RoleDto roleDto);
    void save(RoleCreateDto roleCreateDto);
    void delete(UUID id);
    Role getById(UUID id);
    List<Role> getAll();
    Role getByName(String name);
}
