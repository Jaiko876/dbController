package ru.quick.approval.system.dbcontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import ru.quick.approval.system.api.controller.RoleApi;
import ru.quick.approval.system.api.controller.RoleidApi;
import ru.quick.approval.system.api.model.Role;
import ru.quick.approval.system.api.model.User;
import ru.quick.approval.system.api.model.UserWithoutPassword;
import ru.quick.approval.system.dbcontroller.service.RoleService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author Kirill Mikheev
 * @version 1.0
 */

public class RoleController implements RoleApi, RoleidApi {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<Void> addRole(@Valid Role role) {
        return null;
    }

    @Override
    public ResponseEntity<List<Role>> allRoles() {
        return null;
    }

    @Override
    public ResponseEntity<List<UserWithoutPassword>> getUsersByRoleId(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Role> getRoleById(Integer id) {
        return null;
    }


}
