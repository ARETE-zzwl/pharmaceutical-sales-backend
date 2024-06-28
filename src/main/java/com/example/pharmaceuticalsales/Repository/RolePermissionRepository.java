package com.example.pharmaceuticalsales.Repository;

import com.example.pharmaceuticalsales.Model.RolePermission;
import com.example.pharmaceuticalsales.Model.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionId> {
}
