package ru.quick.approval.system.dbcontroller.service;

import org.jooq.demo.db.tables.records.RoleQasRecord;
import org.jooq.demo.db.tables.records.UserQasRecord;
import org.jooq.demo.db.tables.records.UserRoleRecord;
import org.springframework.beans.factory.annotation.Autowired;
import ru.quick.approval.system.api.model.Role;
import ru.quick.approval.system.api.model.UserWithoutPassword;
import ru.quick.approval.system.dbcontroller.dao.RoleDao;
import ru.quick.approval.system.dbcontroller.dao.UserDao;
import ru.quick.approval.system.dbcontroller.dao.UserRoleDao;
import ru.quick.approval.system.dbcontroller.service.iservice.IRoleService;
import ru.quick.approval.system.dbcontroller.staff.Interpreters;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для обработки запросов RoleController
 * @author Kirill Mikheev
 * @version 1.0
 */

public class RoleService implements IRoleService {

    private RoleDao roleDao;
    private UserDao userDao;
    private UserRoleDao userRoleDao;

    @Autowired
    public RoleService(RoleDao roleDao, UserDao userDao, UserRoleDao userRoleDao){
        this.roleDao = roleDao;
        this.userDao = userDao;
        this.userRoleDao = userRoleDao;
    }

    /**
     * Добавляет новую роль в таблицу
     * @param role
     * @return true, если все прошло успешно, иначе false
     */
    @Override
    public boolean addRole(Role role) {
        return roleDao.addRole(Interpreters.roleToRoleQasRecord(role));
    }

    /**
     * Выводит список всех ролей
     * @return
     */
    @Override
    public List<Role> allRoles() {
        List<RoleQasRecord> records = roleDao.getAllRoles();
        List<Role> roles = new ArrayList<>();
        for(RoleQasRecord tmp : records){
            roles.add(Interpreters.roleQasRecordToRole(tmp));
        }
        return roles;
    }

    /**
     * Возвращает пользователей данной роли
     * @param id
     * @return List<UserWithoutPassword>
     */
    @Override
    public List<UserWithoutPassword> getUsersByRoleId(int id) {
        List<UserWithoutPassword> answer = new ArrayList<>();
        List<UserRoleRecord> records = userRoleDao.getAllUserRoles();
        for(UserRoleRecord tmp : records){
            if(tmp.getRoleId() == id){
                answer.add(Interpreters.userQasRecordToUserWithoutPassword(userDao.getUserById(tmp.getUserId())));
            }
        }
        return answer;
    }

    /**
     * Возвращает роль с заданным id
     * @param id
     * @return Role
     */
    @Override
    public Role getRoleById(int id) {
        return Interpreters.roleQasRecordToRole(roleDao.getRoleById(id));
    }
}
