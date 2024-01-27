package uz.pdp.simline.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.simline.dto.request.RoleCreateDto;
import uz.pdp.simline.dto.request.RoleDto;
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
    public void addRoleToUser(RoleDto roleDto) {
        if (roleDto == null)
            throw new NullOrEmptyException("RoleDto");
        if (roleDto.getUserId() == null)
            throw new NullOrEmptyException("User Id");
        if (Validations.isNullOrEmpty(roleDto.getRole()))
            throw new NullOrEmptyException("Role");
        User user = userRepository.findById(roleDto.getUserId()).orElseThrow(
                () -> new NotFoundException("User")
        );
        user.getRoles().add(roleRepository.findByRole(roleDto.getRole()).orElseThrow(
                () -> new NotFoundException("Role")
        ));
        userRepository.save(user);
    }

    @Override
    public void deleteRoleFromUser(RoleDto roleDto) {
        if (roleDto == null)
            throw new NullOrEmptyException("Role Dto");
        if (roleDto.getUserId() == null)
            throw new NullOrEmptyException("User Id");
        if (Validations.isNullOrEmpty(roleDto.getRole()))
            throw new NullOrEmptyException("Role");
        User user = userRepository.findById(roleDto.getUserId()).orElseThrow(
                () -> new NotFoundException("User")
        );
        Role role1 = roleRepository.findByRole(roleDto.getRole()).orElseThrow(
                () -> new NotFoundException("Role")
        );
        user.getRoles().remove(role1);
        userRepository.save(user);
    }

    @Override
    public void save(RoleCreateDto roleCreateDto) {
        if (roleCreateDto == null)
            throw new NullOrEmptyException("RoleCreateDto");
        if (Validations.isNullOrEmpty(roleCreateDto.getRole()))
            throw new NullOrEmptyException("Role");
        if (Validations.isNullOrEmpty(roleCreateDto.getDescription()))
            throw new NullOrEmptyException("Description");
        if (roleRepository.findByRole(roleCreateDto.getRole()).isPresent())
            throw new AlreadyExistsException("Role");
        roleRepository.save(Role.builder()
                .role(roleCreateDto.getRole())
                .description(roleCreateDto.getDescription())
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
