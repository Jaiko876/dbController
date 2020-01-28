package ru.quick.approval.system.dbcontroller.service;

import org.jooq.demo.db.tables.UserRole;
import org.jooq.demo.db.tables.records.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.quick.approval.system.api.model.*;
import ru.quick.approval.system.dbcontroller.dao.iDao.*;
import ru.quick.approval.system.dbcontroller.service.iservice.IUserService;
import ru.quick.approval.system.dbcontroller.staff.Interpreters;

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
    private final ITaskDao taskDao;
    private final IStatusDao statusDao;
    private final IUserRoleDao userRoleDao;

    @Autowired
    public UserService(IUserDao userDao, IRoleDao roleDao, ITaskDao taskDao, IStatusDao statusDao, IUserRoleDao userRoleDao){
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.taskDao = taskDao;
        this.statusDao = statusDao;
        this.userRoleDao = userRoleDao;
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

    /**
     * Проверяет была ли такая роль в тблице, если нет, то добавляет ее
     * Далее добавляет новую пару в таблицу user_role
     * @param id пользователя, к которому нужно добавить новую роль
     * @param role объект, содержащий данные о роли, которую нужно добавить
     * @return true, если все прошло успешно, иначе false
     */
    @Override
    public boolean addRoleToUserById(int id, Role role) {
        RoleQasRecord roleQasRecord = roleDao.getRoleByName(role.getName());
        if(roleQasRecord == null){
            roleDao.addRole(new RoleQasRecord(0, role.getName()));
            roleQasRecord = roleDao.getRoleByName(role.getName());
        }
        return userRoleDao.addUserRole(new UserRoleRecord(0, id, roleQasRecord.getIdRole()));
    }

    /**
     * Возвращает пользователя с заданным id
     * @param id
     * @return UserWithoutPassword
     */
    @Override
    public UserWithoutPassword getUserById(int id) {
        UserQasRecord record = userDao.getUserById(id);
        if(record == null){
            return null;
        }
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
        return userDao.updateUserById(id, Interpreters.userToUserQasRecord(user));
    }

    /**
     * Возвращает список задач со статусом active у пользователя с заданным id
     * @param id
     * @return List<Task>
     */
    @Override
    @SuppressWarnings("Duplicates")
    public List<Task> getActiveTaskListOfUserById(int id) {
        StatusRecord statusRecord = statusDao.getStatusByName("active");
        List<TaskRecord> taskRecords = taskDao.getAllTasks();
        List<Task> tasks = new ArrayList<>();
        for(TaskRecord tmp : taskRecords){
            if(tmp.getStatusId() == statusRecord.getIdStatus() && tmp.getUserPerformerId() == id){
                Task task = Interpreters.taskRecordToTask(tmp);
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * Возвращает список задач со статусом complete у пользователя с заданным id
     * @param id
     * @return List<Task>
     */
    @Override
    @SuppressWarnings("Duplicates")
    public List<Task> getCompleteTaskListOfUserById(int id) {
        StatusRecord statusRecord = statusDao.getStatusByName("complete");
        List<TaskRecord> taskRecords = taskDao.getAllTasks();
        List<Task> tasks = new ArrayList<>();
        for(TaskRecord tmp : taskRecords){
            if(tmp.getStatusId() == statusRecord.getIdStatus() && tmp.getUserPerformerId() == id){
                Task task = Interpreters.taskRecordToTask(tmp);
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * Возвращает список задач со статусом wait у пользователя с заданным id
     * @param id
     * @return List<Task>
     */
    @Override
    @SuppressWarnings("Duplicates")
    public List<Task> getWaitTaskListOfUserById(int id) {
        StatusRecord statusRecord = statusDao.getStatusByName("wait");
        List<TaskRecord> taskRecords = taskDao.getAllTasks();
        List<Task> tasks = new ArrayList<>();
        for(TaskRecord tmp : taskRecords){
            if(tmp.getStatusId() == statusRecord.getIdStatus() && tmp.getUserPerformerId() == id){
                Task task = Interpreters.taskRecordToTask(tmp);
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * Возвращает список всех задач пользователя с заданным id
     * @param id
     * @return List<Task>
     */
    @Override
    @SuppressWarnings("Duplicates")
    public List<Task> getTaskListOfUserById(Integer id) {
        List<TaskRecord> taskRecords = taskDao.getAllTasks();
        List<Task> tasks = new ArrayList<>();
        for(TaskRecord tmp : taskRecords){
            if(tmp.getUserPerformerId() == id){
                Task task = Interpreters.taskRecordToTask(tmp);
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * Возвращает список всех ролей пользователя с заданны id
     * @param id
     * @return List<Role>
     */
    @Override
    public List<Role> getRoleListOfUserById(Integer id) {
        List<UserRoleRecord> userRoleRecords = userRoleDao.getAllUserRoles();
        List<Role> roles = new ArrayList<>();
        for(UserRoleRecord tmp : userRoleRecords){
            if(tmp.getUserId() == id){
                roles.add(Interpreters.roleQasRecordToRole(roleDao.getRoleById(tmp.getRoleId())));
            }
        }
        return roles;
    }

    @Override
    public boolean login(InlineObject authData) {
        UserQasRecord userQasRecord = userDao.getUserByLogin(authData.getLogin());
        return userQasRecord.getPassword() == authData.getPassword();
    }

    @Override
    public boolean logout() {
        return false;
    }
}
