package ru.quick.approval.system.dbcontroller.dao;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.demo.db.tables.UserQas;
import org.jooq.demo.db.tables.records.UserQasRecord;
import org.springframework.stereotype.Component;
import ru.quick.approval.system.dbcontroller.dao.iDao.IUserDao;

import java.util.ArrayList;
import java.util.List;

/**
 * DAO-класс для работы с таблицей user_qas
 * @author Kirill Mikheev
 * @version 1.0
 */

@Component
public class UserDao implements IUserDao {

    /** Нужно для соединения и работы с бд*/
    private final DSLContext dslContext;

    public UserDao(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    /**
     * Возвращает список всех пользователей в таблице
     * @return List<UserQasRecord>
     */
    @Override
    public List<UserQasRecord> getAllUsers() {
        List<UserQasRecord> answer = new ArrayList<>();
        Result<UserQasRecord> result = dslContext.selectFrom(UserQas.USER_QAS).fetch();
        for (UserQasRecord record : result){
            answer.add(record);
        }
        return answer;
    }

    /**
     * Возвращает пользователя с заданным id
     * @param id
     * @return UserQasRecord
     */
    @Override
    public UserQasRecord getUserById(int id) {
        return dslContext.selectFrom(UserQas.USER_QAS).where(UserQas.USER_QAS.ID_USER.eq(id)).fetchAny();
    }

    /**
     * Обновляет данные для пользователя с заданным id
     * @param id
     * @param newUser объект записи пользователя с новыми данными
     * @return true, если все прошло успешно, иначе false
     */
    @Override
    public boolean updateUserById(int id, UserQasRecord newUser) {
        int response = dslContext.update(UserQas.USER_QAS).set(newUser).where(UserQas.USER_QAS.ID_USER.eq(id)).execute();
        return response == 0;
    }

    /**
     * Добавляет в аблицу нового пользователя
     * @param newUser
     * @return true, если все прошло успешно, иначе false
     */
    @Override
    public boolean addUser(UserQasRecord newUser) {
        int response = dslContext.insertInto(UserQas.USER_QAS).set(newUser).execute();
        return response == 0;
    }
}
