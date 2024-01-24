package uz.pdp.simline.service;

import org.springframework.stereotype.Service;
import uz.pdp.simline.entity.Role;

import java.util.List;
import java.util.UUID;

@Service
public interface RoleService {
    void addRoleToUser(String role,UUID user_id);
    void deleteRoleFromUser(String role,UUID user_id);
    void save(String role,String description);
    void delete(UUID id);
    Role getById(UUID id);
    List<Role> getAll();
    Role getByName(String name);
}
