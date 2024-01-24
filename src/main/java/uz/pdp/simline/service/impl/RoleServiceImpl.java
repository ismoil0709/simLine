package uz.pdp.simline.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.simline.entity.Role;
import uz.pdp.simline.entity.User;
import uz.pdp.simline.exception.AlreadyExistsException;
import uz.pdp.simline.exception.NotFoundException;
import uz.pdp.simline.exception.NullOrEmptyException;
import uz.pdp.simline.repository.RoleRepository;
import uz.pdp.simline.repository.UserRepository;
import uz.pdp.simline.service.RoleService;
import uz.pdp.simline.util.Validations;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public void addRoleToUser(String role, UUID user_id) {
        if (user_id == null)
            throw new NullOrEmptyException("User Id");
        if (Validations.isNullOrEmpty(role))
            throw new NullOrEmptyException("Role");
        User user = userRepository.findById(user_id).orElseThrow(
                () -> new NotFoundException("User")
        );
        user.getRoles().add(roleRepository.findByRole(role).orElseThrow(
                () -> new NotFoundException("Role")
        ));
        userRepository.save(user);
    }

    @Override
    public void deleteRoleFromUser(String role, UUID user_id) {
        if (user_id == null)
            throw new NullOrEmptyException("User Id");
        if (Validations.isNullOrEmpty(role))
            throw new NullOrEmptyException("Role");
        User user = userRepository.findById(user_id).orElseThrow(
                () -> new NotFoundException("User")
        );
        Role role1 = roleRepository.findByRole(role).orElseThrow(
                () -> new NotFoundException("Role")
        );
        user.getRoles().remove(role1);
        userRepository.save(user);
    }

    @Override
    public void save(String role, String description) {
        if (Validations.isNullOrEmpty(role))
            throw new NullOrEmptyException("Role");
        if (Validations.isNullOrEmpty(description))
            throw new NullOrEmptyException("Description");
        if (roleRepository.findByRole(role).isPresent())
            throw new AlreadyExistsException("Role");
        roleRepository.save(Role.builder()
                .role(role)
                .description(description)
                .build());
    }

    @Override
    public void delete(UUID id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        roleRepository.delete(
                roleRepository.findById(id).orElseThrow(
                        () -> new NotFoundException("Role")
                )
        );
    }

    @Override
    public Role getById(UUID id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        return roleRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Role")
        );
    }

    @Override
    public List<Role> getAll() {
        List<Role> all = roleRepository.findAll();
        if (all.isEmpty())
            throw new NullOrEmptyException("Roles");
        return all;
    }

    @Override
    public Role getByName(String name) {
        if (Validations.isNullOrEmpty(name))
            throw new NullOrEmptyException("Name");
        return roleRepository.findByRole(name).orElseThrow(
                () -> new NotFoundException("Role")
        );
    }
}
