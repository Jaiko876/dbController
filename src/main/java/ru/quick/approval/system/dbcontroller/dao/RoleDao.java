package ru.quick.approval.system.dbcontroller.dao;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.demo.db.tables.RoleQas;
import org.jooq.demo.db.tables.records.RoleQasRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.quick.approval.system.dbcontroller.dao.iDao.IRoleDao;

import java.util.ArrayList;
import java.util.List;

/**
 * DAO-класс для работы с таблицей role_qas
 * @author Kirill Mikheev
 * @version 1.0
 */

@Component
public class RoleDao implements IRoleDao {

    /** Нужно для соединения и работы с бд*/
    private final DSLContext dslContext;

    @Autowired
    public RoleDao(DSLContext dslContext){
        this.dslContext = dslContext;
    }

    /**
     * Возвращает список всех ролей
     * @return List<RoleQasRecord>
     */
    @Override
    public List<RoleQasRecord> getAllRoles() {
        List<RoleQasRecord> answer = new ArrayList<>();
        Result<RoleQasRecord> result = dslContext.selectFrom(RoleQas.ROLE_QAS).fetch();
        for (RoleQasRecord record : result){
            answer.add(record);
        }
        return answer;
    }

    /**
     * Возвращает роль с заданным id
     * @param id
     * @return RoleQasRecord
     */
    @Override
    public RoleQasRecord getRoleById(int id) {
        return dslContext.selectFrom(RoleQas.ROLE_QAS).where(RoleQas.ROLE_QAS.ID_ROLE.eq(id)).fetchAny();
    }

    /**
     * Обновляет данные для роли по ее id
     * @param id
     * @param newRole объект записи пользователя с новыми данными
     * @return true, если все прошло успешно, иначе false
     */
    @Override
    public boolean updateRoleById(int id, RoleQasRecord newRole) {
        int response = dslContext.update(RoleQas.ROLE_QAS).set(newRole).where(RoleQas.ROLE_QAS.ID_ROLE.eq(id)).execute();
        return response == 0;
    }

    /**
     * Добавляет новую роль
     * @param newRole
     * @return true, если все прошло успешно, иначе false
     */
    @Override
    public boolean addRole(RoleQasRecord newRole) {
        int response = dslContext.insertInto(RoleQas.ROLE_QAS).set(newRole).execute();
        return response == 0;
    }
}
