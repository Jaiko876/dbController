package com.example.demo.dao;

import com.example.demo.pojo.RoleDTO;
import org.jooq.DSLContext;
import org.jooq.demo.db.tables.Role;
import org.jooq.demo.db.tables.records.RoleRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Тестовая даошка для работы с таблицей ролей
 * @author Kirill Mikheev
 * @version 1.0
 */

@Service
public class RoleDAO {

    Logger logger = LoggerFactory.getLogger(RoleDAO.class);

    /** Нужно для соединения и работы с бд*/
    private final DSLContext dslContext;

    public RoleDAO(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    /**
     * Получение роли по ее айдишнику в базе
     * @param id
     * @return Трансферный объект, который нельзя изменить
     */
    public RoleDTO getRoleById(int id) {
        //TODO: Сделать проверку на то, что объект был получен
        RoleRecord roleRecord = dslContext.selectFrom(Role.ROLE).where(Role.ROLE.ROLE_ID.eq(1)).fetchAny();
        return new RoleDTO(roleRecord);
    }

    /**
     * Добавляет в таблицу ролей новую роль
     * @param role
     * @return true, если все прошло успешно, иначе false
     */
    public boolean insertRole(RoleDTO role) {
        logger.info(dslContext.toString());
        dslContext.insertInto(Role.ROLE).values(new RoleRecord(role.getRoleId(), role.getName()));
        //TODO: Сделать проверку на то, что добавление прошло успешно
        return true;
    }

    /**
     * Удаляет роль с заданным айдишником из таблицы
     * @param id
     * @return true, если все прошло успешно, иначе false
     */
    public boolean deleteRoleById(int id) {
        dslContext.delete(Role.ROLE).where(Role.ROLE.ROLE_ID.eq(id));
        //TODO: Сделать проверку на то, что удаление прошло успешно
        return true;
    }
}
