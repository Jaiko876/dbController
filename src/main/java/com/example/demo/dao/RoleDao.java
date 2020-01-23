package com.example.demo.dao;

import com.example.demo.dto.RoleDTO;

public interface RoleDao {
    RoleDTO getRoleById(int id);
    boolean insertRole(RoleDTO role);
    boolean deleteRoleById(int id);
}
