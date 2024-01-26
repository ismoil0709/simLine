package uz.pdp.simline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.simline.dto.respone.SuccessResponse;
import uz.pdp.simline.service.RoleService;
import uz.pdp.simline.util.annotations.Role;

import java.util.UUID;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    @PostMapping("/add")
    public ResponseEntity<?> addRole(@Role String role, UUID user_id){
        roleService.addRoleToUser(role,user_id);
        return ResponseEntity.ok(new SuccessResponse("Role added"));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRole(@Role String role, UUID user_id) {
        roleService.deleteRoleFromUser(role, user_id);
        return ResponseEntity.ok(new SuccessResponse("Role deleted"));
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveRole(@Role String role, String description){
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
