package ru.quick.approval.system.dbcontroller.service;

import org.jooq.demo.db.tables.records.StatusRecord;
import org.jooq.demo.db.tables.records.TaskRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.quick.approval.system.api.model.Task;
import ru.quick.approval.system.dbcontroller.dao.StatusDao;
import ru.quick.approval.system.dbcontroller.dao.TaskDao;
import ru.quick.approval.system.dbcontroller.service.iservice.ITaskService;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с TaskController
 * @author Игорь М
 */

@Service
public class TaskService implements ITaskService {
    private final TaskDao taskDao;
    private final StatusDao statusDao;


    @Autowired
    private TaskService(TaskDao taskDao, StatusDao statusDao) {
        this.taskDao = taskDao;
        this.statusDao = statusDao;
    }

    /**
     * Метод, для перевода Record в Pojo
     * @param taskRecord - Record
     * @return - возвращает Pojo
     */

    static Task toPojo(TaskRecord taskRecord) {
        Task task = new Task();
        task.setIdTask(taskRecord.component1());
        task.setProcessId(taskRecord.component2());
        task.setUserPerformerId(taskRecord.component3());
        task.setRolePerformerId(taskRecord.component4());
        task.setDateStart(taskRecord.component5());
        task.setDateEndPlanning(taskRecord.component6());
        task.setDateEndFact(taskRecord.component7());
        task.setStatusId(taskRecord.component8());
        return task;
    }

    /**
     * Метод, для перевода Pojo в Record
     * @param task - Pojo
     * @return - возвращает Record
     */

    static TaskRecord toTaskRecord(Task task) {
        TaskRecord taskRecord = new TaskRecord();
        taskRecord.setIdTask(task.getIdTask());
        taskRecord.setProcessId(task.getProcessId());
        taskRecord.setUserPerformerId(task.getUserPerformerId());
        taskRecord.setRolePerformerId(task.getRolePerformerId());
        taskRecord.setDateStart(task.getDateStart());
        taskRecord.setDateEndPlanning(task.getDateEndPlanning());
        taskRecord.setDateEndFact(task.getDateEndFact());
        taskRecord.setStatusId(task.getStatusId());
        return taskRecord;
    }

    /**
     * Метод возвращает List всех объектов Task
     * @return - List<Task>
     */
    @Override
    public List<Task> getAllTask() {
        List<Task> taskList = new ArrayList<>();
        List<TaskRecord> taskRecords = taskDao.getAllTasks();
        for (TaskRecord taskRecord: taskRecords) {
            taskList.add(toPojo(taskRecord));
        }
        return taskList;
    }

    /**
     * Возвращает объект Task по id
     * @param id - id таски
     * @return - объект типа Task
     */
    @Override
    public Task getTaskById(int id) {
        return toPojo(taskDao.getTaskById(id));
    }

    /**
     * Метод апдейтит Task по id
     * @param id - id такси
     * @param task - объект типа Task
     * @return - true/false
     */
    @Override
    public boolean updateTaskById(int id, Task task) {
        return taskDao.updateTaskById(id, toTaskRecord(task));
    }

    /**
     * Метод присваивает Task статус Sended
     * @param id - id таски
     * @return - true/false
     */
    @Override
    public boolean updateTaskByIdSended(int id) {
        Task task = toPojo(taskDao.getTaskById(id));
        StatusRecord statusRecord = statusDao.getStatusByName("Sended");
        task.setStatusId(statusRecord.getIdStatus());
        return updateTaskById(id, task);
    }

    /**
     * Метод присваивает Task статус Agreed
     * @param id - id таски
     * @return - true/false
     */
    @Override
    public boolean updateTaskByIdAgreed(int id) {
        Task task = toPojo(taskDao.getTaskById(id));
        StatusRecord statusRecord = statusDao.getStatusByName("Agreed");
        task.setStatusId(statusRecord.getIdStatus());
        return updateTaskById(id, task);
    }

    /**
     * Метод присваивает Task статус Active
     * @param id - id таски
     * @return - true/false
     */
    @Override
    public boolean updateTaskByIdActive(int id) {
        Task task = toPojo(taskDao.getTaskById(id));
        StatusRecord statusRecord = statusDao.getStatusByName("Active");
        task.setStatusId(statusRecord.getIdStatus());
        return updateTaskById(id, task);
    }

    /**
     * Метод присваивает Task статус Denied
     * @param id - id таски
     * @return - true/false
     */
    @Override
    public boolean updateTaskByIdDenied(int id) {
        Task task = toPojo(taskDao.getTaskById(id));
        StatusRecord statusRecord = statusDao.getStatusByName("Denied");
        task.setStatusId(statusRecord.getIdStatus());
        return updateTaskById(id, task);
    }

    /**
     * Метод присваивает Task статус Canceled
     * @param id - id таски
     * @return - true/false
     */
    @Override
    public boolean updateTaskByIdCanceled(int id) {
        Task task = toPojo(taskDao.getTaskById(id));
        StatusRecord statusRecord = statusDao.getStatusByName("Canceled");
        task.setStatusId(statusRecord.getIdStatus());
        return updateTaskById(id, task);
    }
}
