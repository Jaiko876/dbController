package ru.quick.approval.system.dbcontroller.dao;

import ru.quick.approval.system.dbcontroller.dao.iDao.IRoleDao;
import ru.quick.approval.system.dbcontroller.dto.RoleDTO;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.demo.db.tables.Role;
import org.jooq.demo.db.tables.records.RoleRecord;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Тестовая даошка для работы с таблицей ролей
 * @author Kirill Mikheev
 * @version 1.0
 */

@Service
public class RoleDao implements IRoleDao {

    /** Нужно для соединения и работы с бд*/
    private final DSLContext dslContext;

    public RoleDao(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    /**
     * Метод получает содержимое всей таблицы ролей в виде списка
     */
    @Override
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
    @Override
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
    @Override
    public boolean insertRole(RoleDTO role) {
        RoleRecord roleRecord = new RoleRecord(role.getRoleId(), role.getName());
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
    @Override
    public boolean deleteRoleById(int id) {
        dslContext.delete(Role.ROLE).where(Role.ROLE.ROLE_ID.eq(id)).execute();
        //TODO: Сделать проверку на то, что удаление прошло успешно
        return true;
    }
}