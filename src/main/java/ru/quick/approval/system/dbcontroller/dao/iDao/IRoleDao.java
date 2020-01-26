package ru.quick.approval.system.dbcontroller.dao.iDao;

import ru.quick.approval.system.dbcontroller.dto.RoleDTO;

import java.util.List;

/**
 * Интерфейс даошки для таблицы ролей
 */
public interface IRoleDao {
    /**
     * Метод получает содержимое всей таблицы ролей в виде списка
     */
    List<RoleDTO> getRoles();

    /**
     * Получение роли по ее айдишнику в базе
     * @param id
     * @return Трансферный объект, который нельзя изменить
     */
    RoleDTO getRoleById(int id);

    /**
     * Добавляет в таблицу ролей новую роль
     * @param role
     * @return true, если все прошло успешно, иначе false
     */
    boolean insertRole(RoleDTO role);

    /**
     * Удаляет роль с заданным айдишником из таблицы
     * @param id
     * @return true, если все прошло успешно, иначе false
     */
    boolean deleteRoleById(int id);
}
