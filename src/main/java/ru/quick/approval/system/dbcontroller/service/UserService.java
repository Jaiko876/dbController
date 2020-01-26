package ru.quick.approval.system.dbcontroller.service;

import org.jooq.demo.db.tables.records.UserQasRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.quick.approval.system.api.model.Role;
import ru.quick.approval.system.api.model.User;
import ru.quick.approval.system.api.model.UserWithoutPassword;
import ru.quick.approval.system.dbcontroller.dao.iDao.IRoleDao;
import ru.quick.approval.system.dbcontroller.dao.iDao.IUserDao;
import ru.quick.approval.system.dbcontroller.service.iservice.IUserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для обработки запросов UserController
 * @author Kirill Mikheev
 * @version 1.0
 */

@Service
public class UserService implements IUserService {

    private final IUserDao userDao;
    private final IRoleDao roleDao;

    @Autowired
    public UserService(IUserDao userDao, IRoleDao roleDao){
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    /**
     * Возвращает список всех пользователей
     * @return List<UserWithoutPassword>
     */
    @Override
    public List<UserWithoutPassword> allUsers() {
        List<UserQasRecord> records = userDao.getAllUsers();
        List<UserWithoutPassword> answer = new ArrayList<>();
        for (UserQasRecord record : records) {
            UserWithoutPassword member = new UserWithoutPassword();
            member.setIdUser(record.getIdUser());
            member.setFio(record.getFio());
            member.setEmail(record.getEmail());
            member.setLogin(record.getLogin());
            member.setTelegramChatId(record.getTelegramChatId());
            answer.add(member);
        }
        return answer;
    }

    /**
     * Добавляет нового пользователя
     * @param newUser
     * @return true, если все прошло успешно, иначе false
     */
    @Override
    public boolean addUser(User newUser) {
        UserQasRecord record = new UserQasRecord(
                newUser.getIdUser(),
                newUser.getFio(),
                newUser.getLogin(),
                newUser.getPassword(),
                newUser.getEmail(),
                newUser.getTelegramChatId()
        );
        return userDao.addUser(record);
    }

    @Override
    public boolean addRoleToUserById(int id, Role role) {
        return false;
    }

    /**
     * Возвращает пользователя с заданным id
     * @param id
     * @return UserWithoutPassword
     */
    @Override
    public UserWithoutPassword getUserById(int id) {
        UserQasRecord record = userDao.getUserById(id);
        UserWithoutPassword answer = new UserWithoutPassword();
        answer.setIdUser(record.getIdUser());
        answer.setFio(record.getFio());
        answer.setEmail(record.getEmail());
        answer.setLogin(record.getLogin());
        answer.setTelegramChatId(record.getTelegramChatId());
        return answer;
    }

    /**
     * Обновление данных пользователя по его id
     * @param id
     * @param user объект с новыми данными
     * @return true, если все прошло успешно, иначе false
     */
    @Override
    public boolean updateUserById(int id, User user) {
        return false;
    }
}
