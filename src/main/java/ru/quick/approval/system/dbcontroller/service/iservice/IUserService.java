package ru.quick.approval.system.dbcontroller.service.iservice;

import ru.quick.approval.system.api.model.*;

import java.util.List;

/**
 * Интерфейс сервиса для обработки запросов UserController
 * @author Kirill Mikheev
 * @version 1.0
 */

public interface IUserService {

    List<UserWithoutPassword> allUsers();

    boolean addUser(User newUser);

    boolean addRoleToUserById(int id, Role role);

    UserWithoutPassword getUserById(int id);

    boolean updateUserById(int id, User user);

    List<Task> getActiveTaskListOfUserById(int id);

    List<Task> getCompleteTaskListOfUserById(int id);

    List<Task> getWaitTaskListOfUserById(int id);

    List<Task> getTaskListOfUserById(Integer id);

    List<Role> getRoleListOfUserById(Integer id);

    boolean login(InlineObject authData);

    boolean logout();
}
