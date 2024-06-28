package com.example.pharmaceuticalsales.Service;


import com.example.pharmaceuticalsales.Exception.ResourceNotFoundException;
import com.example.pharmaceuticalsales.Model.RolePermission;
import com.example.pharmaceuticalsales.Model.RolePermissionId;
import com.example.pharmaceuticalsales.Repository.RolePermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;

    @Autowired
    public RolePermissionService(RolePermissionRepository rolePermissionRepository) {
        this.rolePermissionRepository = rolePermissionRepository;
    }

    public List<RolePermission> getAllRolePermissions() {
        return rolePermissionRepository.findAll();
    }

    public RolePermission saveRolePermission(RolePermission rolePermission) {
        return rolePermissionRepository.save(rolePermission);
    }

    public RolePermission updateRolePermission(Long roleId, Long permissionId, RolePermission rolePermission) {
        RolePermission existingRolePermission = rolePermissionRepository.findById(new RolePermissionId(roleId, permissionId)).orElseThrow(() -> new ResourceNotFoundException("RolePermission not found"));
        existingRolePermission.setRole(rolePermission.getRole());
        existingRolePermission.setPermission(rolePermission.getPermission());
        return rolePermissionRepository.save(existingRolePermission);
    }

    public void deleteRolePermission(Long roleId, Long permissionId) {
        rolePermissionRepository.deleteById(new RolePermissionId(roleId, permissionId));
    }
}
