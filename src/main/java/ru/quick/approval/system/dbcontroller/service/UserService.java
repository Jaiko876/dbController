package ru.quick.approval.system.dbcontroller.service;

import org.jooq.demo.db.tables.records.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.quick.approval.system.api.model.*;
import ru.quick.approval.system.dbcontroller.dao.iDao.*;
import ru.quick.approval.system.dbcontroller.service.iservice.IUserService;
import ru.quick.approval.system.dbcontroller.translator.ITranslator;

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

    private final ITranslator<TaskRecord, Task> taskTranslator;
    private final ITranslator<RoleQasRecord, Role> roleTranslator;
    private final ITranslator<UserQasRecord, User> userTranslator;
    private final ITranslator<UserQasRecord, UserWithoutPassword> userWithoutPasswordTranslator;

    @Autowired
    public UserService(IUserDao userDao, IRoleDao roleDao, ITaskDao taskDao, IStatusDao statusDao, IUserRoleDao userRoleDao,
                       ITranslator<UserQasRecord, User> userTranslator,
                       ITranslator<RoleQasRecord, Role> roleTranslator,
                       ITranslator<TaskRecord, Task> taskTranslator,
                       ITranslator<UserQasRecord, UserWithoutPassword> userWithoutPasswordTranslator){
        this.taskTranslator = taskTranslator;
        this.userWithoutPasswordTranslator = userWithoutPasswordTranslator;
        this.userTranslator = userTranslator;
        this.roleTranslator = roleTranslator;
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
            answer.add(userWithoutPasswordTranslator.translate(record));
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
        return userDao.addUser(userTranslator.reverseTranslate(newUser));
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

        return userWithoutPasswordTranslator.translate(record);
    }

    /**
     * Обновление данных пользователя по его id
     * @param id
     * @param user объект с новыми данными
     * @return true, если все прошло успешно, иначе false
     */
    @Override
    public boolean updateUserById(int id, User user) {
        return userDao.updateUserById(id, userTranslator.reverseTranslate(user));
    }

    /**
     * Возвращает список задач со статусом active у пользователя с заданным id
     * @param id
     * @return List<Task>
     */
    @Override
    @SuppressWarnings("Duplicates")
    public List<Task> getActiveTaskListOfUserById(int id) {
        StatusRecord active = statusDao.getStatusByName("active");
        StatusRecord sended = statusDao.getStatusByName("sended");
        List<TaskRecord> taskRecords = taskDao.getAllTasks();
        List<Task> tasks = new ArrayList<>();
        for(TaskRecord tmp : taskRecords){
            if((tmp.getStatusId() == active.getIdStatus() || tmp.getStatusId() == sended.getIdStatus()) && tmp.getUserPerformerId() == id){
                Task task = taskTranslator.translate(tmp);
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
        StatusRecord agreed = statusDao.getStatusByName("agreed");
        StatusRecord denied = statusDao.getStatusByName("denied");
        List<TaskRecord> taskRecords = taskDao.getAllTasks();
        List<Task> tasks = new ArrayList<>();
        for(TaskRecord tmp : taskRecords){
            if((tmp.getStatusId() == agreed.getIdStatus() || tmp.getStatusId() == denied.getIdStatus()) && tmp.getUserPerformerId() == id){
                Task task = taskTranslator.translate(tmp);
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
                Task task = taskTranslator.translate(tmp);
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
                Task task = taskTranslator.translate(tmp);
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
                roles.add(roleTranslator.translate(roleDao.getRoleById(tmp.getRoleId())));
            }
        }
        return roles;
    }

    @Override
    public boolean login(InlineObject authData) {
        UserQasRecord userQasRecord = userDao.getUserByLogin(authData.getLogin());
        return userQasRecord.getPassword() == authData.getPassword();
    }
}
