package uz.pdp.simline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.simline.dto.respone.SuccessResponse;
import uz.pdp.simline.service.RoleService;

import java.util.UUID;

@RestController
//@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    @PostMapping("/add")
    public ResponseEntity<?> addRole(String role, UUID user_id){
        roleService.addRoleToUser(role,user_id);
        return ResponseEntity.ok(new SuccessResponse("Role added"));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRole(String role, UUID user_id) {
        roleService.deleteRoleFromUser(role, user_id);
        return ResponseEntity.ok(new SuccessResponse("Role deleted"));
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveRole(String role, String description){
        roleService.save(role,description);
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
