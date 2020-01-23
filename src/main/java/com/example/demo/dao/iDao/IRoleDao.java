package com.example.demo.dao.iDao;

import com.example.demo.dao.RoleDao;
import com.example.demo.dto.RoleDTO;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.demo.db.tables.Role;
import org.jooq.demo.db.tables.records.RoleRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Тестовая даошка для работы с таблицей ролей
 * @author Kirill Mikheev
 * @version 1.0
 */

@Service
public class IRoleDao implements RoleDao {

    private Logger logger = LoggerFactory.getLogger(IRoleDao.class);

    /** Нужно для соединения и работы с бд*/
    private final DSLContext dslContext;

    public IRoleDao(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public List<RoleDTO> getRoles() {
        List<RoleDTO> roleDTOList = new ArrayList<>();
        Result<RoleRecord> roleRecord = dslContext.selectFrom(Role.ROLE).fetch();
        for (RoleRecord record : roleRecord) {
            RoleDTO roleDTO = new RoleDTO(record);
            roleDTOList.add(roleDTO);
        }
        return roleDTOList;
    }

    /**
     * Получение роли по ее айдишнику в базе
     * @param id
     * @return Трансферный объект, который нельзя изменить
     */
    public RoleDTO getRoleById(int id) {
        //TODO: Сделать проверку на то, что объект был получен
        RoleRecord roleRecord = dslContext.selectFrom(Role.ROLE).where(Role.ROLE.ROLE_ID.eq(id)).fetchAny();
        return new RoleDTO(roleRecord);
    }

    /**
     * Добавляет в таблицу ролей новую роль
     * @param role
     * @return true, если все прошло успешно, иначе false
     */
    public boolean insertRole(RoleDTO role) {
        RoleRecord roleRecord = new RoleRecord(role.getRoleId(), role.getName());
                logger.info(dslContext.toString());
        dslContext.insertInto(Role.ROLE, Role.ROLE.ROLE_ID, Role.ROLE.NAME)
                .values(roleRecord.component1(),roleRecord.component2()).execute();
        //TODO: Сделать проверку на то, что добавление прошло успешно
        return true;
    }

    /**
     * Удаляет роль с заданным айдишником из таблицы
     * @param id
     * @return true, если все прошло успешно, иначе false
     */
    public boolean deleteRoleById(int id) {
        dslContext.delete(Role.ROLE).where(Role.ROLE.ROLE_ID.eq(id)).execute();
        //TODO: Сделать проверку на то, что удаление прошло успешно
        return true;
    }
}