package ru.quick.approval.system.dbcontroller.service;

import org.jooq.demo.db.tables.records.UserQasRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.quick.approval.system.api.model.UserWithoutPassword;
import ru.quick.approval.system.dbcontroller.dao.iDao.IUserDao;
import ru.quick.approval.system.dbcontroller.dto.UserDto;
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

    private IUserDao userDao;

    @Autowired
    public UserService(IUserDao userDao){
        this.userDao = userDao;
    }

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
}
