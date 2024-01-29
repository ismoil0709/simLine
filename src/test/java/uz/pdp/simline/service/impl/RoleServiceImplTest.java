package uz.pdp.simline.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uz.pdp.simline.dto.request.RoleCreateDto;
import uz.pdp.simline.dto.request.RoleDto;
import uz.pdp.simline.entity.Role;
import uz.pdp.simline.entity.User;
import uz.pdp.simline.exception.NullOrEmptyException;
import uz.pdp.simline.repository.RoleRepository;
import uz.pdp.simline.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RoleServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addRoleToUser_Success() {
        RoleDto roleDto = new RoleDto("ROLE_ADMIN", UUID.randomUUID());
        User user = new User();
        user.setId(roleDto.getUserId());
        when(userRepository.findById(roleDto.getUserId())).thenReturn(Optional.of(user));

        Role role = new Role();
        role.setRole(roleDto.getRole());
        when(roleRepository.findByRole(roleDto.getRole())).thenReturn(Optional.of(role));

        assertDoesNotThrow(() -> roleService.addRoleToUser(roleDto));

        verify(userRepository, times(1)).findById(roleDto.getUserId());
        verify(roleRepository, times(1)).findByRole(roleDto.getRole());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void deleteRoleFromUser_ThrowsNullOrEmptyException() {
        RoleDto roleDto1 = null;
        assertThrows(NullOrEmptyException.class, () -> roleService.deleteRoleFromUser(roleDto1));

        RoleDto roleDto2 = new RoleDto(null, null);
        assertThrows(NullOrEmptyException.class, () -> roleService.deleteRoleFromUser(roleDto2));

        RoleDto roleDto3 = new RoleDto("", UUID.randomUUID());
        assertThrows(NullOrEmptyException.class, () -> roleService.deleteRoleFromUser(roleDto3));
    }

    @Test
    void save_Success() {
        RoleCreateDto roleCreateDto = new RoleCreateDto("ROLE_USER", "User role");
        when(roleRepository.findByRole(roleCreateDto.getRole())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> roleService.save(roleCreateDto));

        verify(roleRepository, times(1)).findByRole(roleCreateDto.getRole());
        verify(roleRepository, times(1)).save(any(Role.class));
    }

    @Test
    void delete_Success() {
        UUID roleId = UUID.randomUUID();
        when(roleRepository.findById(roleId)).thenReturn(Optional.of(new Role()));

        assertDoesNotThrow(() -> roleService.delete(roleId));

        verify(roleRepository, times(1)).findById(roleId);
        verify(roleRepository, times(1)).delete(any(Role.class));
    }

    @Test
    void getById_ThrowsNullOrEmptyException() {
        UUID roleId = null;
        assertThrows(NullOrEmptyException.class, () -> roleService.getById(roleId));
    }

    @Test
    void getAll_ThrowsNullOrEmptyException() {
        when(roleRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(NullOrEmptyException.class, () -> roleService.getAll());
    }

    @Test
    void getByName_ThrowsNullOrEmptyException() {
        assertThrows(NullOrEmptyException.class, () -> roleService.getByName(null));
        assertThrows(NullOrEmptyException.class, () -> roleService.getByName(""));
    }
}
