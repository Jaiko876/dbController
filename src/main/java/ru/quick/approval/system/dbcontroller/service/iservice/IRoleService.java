package ru.quick.approval.system.dbcontroller.service.iservice;

import ru.quick.approval.system.api.model.Role;
import ru.quick.approval.system.api.model.UserWithoutPassword;

import java.util.List;

/**
 * Интерфейс сервиса для обработки запросов RoleController
 * @author Kirill Mikheev
 * @version 1.0
 */

public interface IRoleService {

    boolean addRole(Role role);

    List<Role> allRoles();

    List<UserWithoutPassword> getUsersByRoleId(int id);

    Role getRoleById(int id);
}
