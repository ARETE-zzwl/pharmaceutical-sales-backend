package com.example.pharmaceuticalsales.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class RolePermissionId implements Serializable {

    private Long roleId;
    private Long permissionId;

    public RolePermissionId(Long roleId, Long permissionId){
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public RolePermissionId() {

    }


    // Getters and Setters, equals() and hashCode()


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolePermissionId that = (RolePermissionId) o;
        return Objects.equals(roleId, that.roleId) && Objects.equals(permissionId, that.permissionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, permissionId);
    }
}
