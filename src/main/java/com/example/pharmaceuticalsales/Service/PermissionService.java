package com.example.pharmaceuticalsales.Service;


import com.example.pharmaceuticalsales.Exception.ResourceNotFoundException;
import com.example.pharmaceuticalsales.Model.Permission;
import com.example.pharmaceuticalsales.Repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    public Permission savePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    public Permission updatePermission(Long id, Permission permission) {
        Permission existingPermission = permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Permission not found"));
        existingPermission.setPermissionName(permission.getPermissionName());
        return permissionRepository.save(existingPermission);
    }

    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }
}
