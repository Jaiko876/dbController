package ru.quick.approval.system.dbcontroller.service.iservice;

import ru.quick.approval.system.api.model.Task;

import java.util.List;

public interface ITaskService {
    List<Task> getAllTask();
    Task getTaskById(int id);
    boolean updateTaskById(int id, Task task);
    boolean updateTaskByIdSended(int id);
    boolean updateTaskByIdAgreed(int id);
    boolean updateTaskByIdActive(int id);
    boolean updateTaskByIdDenied(int id);
    boolean updateTaskByIdCanceled(int id);

}
