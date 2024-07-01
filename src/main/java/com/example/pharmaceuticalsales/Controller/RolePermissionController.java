package com.example.pharmaceuticalsales.Controller;


import com.example.pharmaceuticalsales.Model.RolePermission;
import com.example.pharmaceuticalsales.Service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/rolepermissions")
public class RolePermissionController {

    private final RolePermissionService rolePermissionService;

    @Autowired
    public RolePermissionController(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @GetMapping
    public ResponseEntity<List<RolePermission>> getAllRolePermissions() {
        return ResponseEntity.ok(rolePermissionService.getAllRolePermissions());
    }

    @PostMapping
    public ResponseEntity<RolePermission> createRolePermission(@RequestBody RolePermission rolePermission) {
        return ResponseEntity.ok(rolePermissionService.saveRolePermission(rolePermission));
    }

    @PutMapping("/{roleId}/{permissionId}")
    public ResponseEntity<RolePermission> updateRolePermission(@PathVariable Long roleId, @PathVariable Long permissionId, @RequestBody RolePermission rolePermission) {
        return ResponseEntity.ok(rolePermissionService.updateRolePermission(roleId, permissionId, rolePermission));
    }

    @DeleteMapping("/{roleId}/{permissionId}")
    public ResponseEntity<Void> deleteRolePermission(@PathVariable Long roleId, @PathVariable Long permissionId) {
        rolePermissionService.deleteRolePermission(roleId, permissionId);
        return ResponseEntity.noContent().build();
    }
}
