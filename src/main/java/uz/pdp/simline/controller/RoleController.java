package uz.pdp.simline.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.simline.dto.request.RoleCreateDto;
import uz.pdp.simline.dto.request.RoleDto;
import uz.pdp.simline.dto.respone.SuccessResponse;
import uz.pdp.simline.service.RoleService;
import uz.pdp.simline.util.annotations.Role;

import java.util.UUID;

@RestController
//@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    @PostMapping("/add")
    public ResponseEntity<?> addRole(@RequestBody @Valid RoleDto roleDto){
        roleService.addRoleToUser(roleDto);
        return ResponseEntity.ok(new SuccessResponse("Role added"));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRole(@RequestBody @Valid RoleDto roleDto) {
        roleService.deleteRoleFromUser(roleDto);
        return ResponseEntity.ok(new SuccessResponse("Role deleted"));
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveRole(@RequestBody @Valid RoleCreateDto roleCreateDto){
        roleService.save(roleCreateDto);
        return ResponseEntity.ok(new SuccessResponse("Role saved"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable UUID id){
        roleService.delete(id);
        return ResponseEntity.ok(new SuccessResponse("Role deleted"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id){
        return ResponseEntity.ok(roleService.getById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(roleService.getAll());
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name){
        return ResponseEntity.ok(roleService.getByName(name));
    }
}
