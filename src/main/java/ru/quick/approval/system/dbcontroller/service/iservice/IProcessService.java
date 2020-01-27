package ru.quick.approval.system.dbcontroller.service.iservice;

import ru.quick.approval.system.api.model.Process;
import ru.quick.approval.system.api.model.StatusType;
import ru.quick.approval.system.api.model.Task;

import java.util.List;

public interface IProcessService {
    List<Process> getAllProcesses();
    Process getProcessById(int id);
    boolean updateProcessById(int id, Process process);
    List<Task> getAllTaskByProcessId(int id);
    StatusType getProcessStatusById(int process_id);
    boolean createNewTaskByUserId(int process_id, int user_id, Task task);
    boolean createNewTaskByRoleId(int process_id, int role_id, Task task);
    boolean createNewProcessByProcessType(int process_type_id, Process process);
    List<Process> getProcessStatusActive();
    List<Process> getProcessStatusComplete();
}
