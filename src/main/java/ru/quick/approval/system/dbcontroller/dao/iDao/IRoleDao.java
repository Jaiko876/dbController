package ru.quick.approval.system.dbcontroller.dao.iDao;

import org.jooq.demo.db.tables.records.RoleQasRecord;

import java.util.List;

/**
 * Интерфейс DAO-класса для работы с таблией role_qas
 * @author Kirill Mikheev
 * @version 1.0
 */

public interface IRoleDao {

    List<RoleQasRecord> getAllRoles();

    RoleQasRecord getRoleById(int id);

    boolean updateRoleById(int id, RoleQasRecord newRole);

    boolean addRole(RoleQasRecord newRole);

}
