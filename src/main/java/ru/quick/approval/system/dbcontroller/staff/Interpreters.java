package ru.quick.approval.system.dbcontroller.staff;

import org.jooq.demo.db.tables.records.*;
import ru.quick.approval.system.api.model.*;
import ru.quick.approval.system.api.model.Process;

/**
 * Класс со статическими методами для перевода из объектов,
 * генерируемых JOOQ'ом, в объекты, генерируемые OpenApi, и обратно
 * Названия говорят сами за себя
 * @author Kirill Mikheev
 * @version 1.0
 */

public class Interpreters {

    @SuppressWarnings("Duplicates")
    public static Process processRecordToProcess(ProcessRecord processRecord) {
        Process process = new Process();
        process.setIdProcess(processRecord.getIdProcess());
        process.setProcessTypeId(processRecord.getProcessTypeId());
        process.setName(processRecord.getName());
        process.setDescription(processRecord.getDescription());
        process.setUserStartId(processRecord.getUserStartId());
        process.setDateStart(processRecord.getDateStart());
        process.setDateEndPlanning(processRecord.getDateEndPlanning());
        process.setDateEndFact(processRecord.getDateEndFact());
        process.setStatusId(processRecord.getStatusId());
        return process;
    }

    @SuppressWarnings("Duplicates")
    public static ProcessRecord processToProcessRecord(Process process) {
        ProcessRecord processRecord = new ProcessRecord();
        processRecord.setIdProcess(process.getIdProcess());
        processRecord.setProcessTypeId(process.getProcessTypeId());
        processRecord.setName(process.getName());
        processRecord.setDescription(process.getDescription());
        processRecord.setUserStartId(process.getUserStartId());
        processRecord.setDateStart(process.getDateStart());
        processRecord.setDateEndPlanning(process.getDateEndPlanning());
        processRecord.setDateEndFact(process.getDateEndFact());
        processRecord.setStatusId(process.getStatusId());
        return processRecord;
    }

    @SuppressWarnings("Duplicates")
    public static ProcessStage processStageToProcessStageRecord(ProcessStageRecord processStageRecord) {
        ProcessStage processStage = new ProcessStage();
        processStage.setIdProcessStage(processStageRecord.getIdProcessStage());
        processStage.setProcessTypeId(processStageRecord.getProcessTypeId());
        processStage.setStage(processStageRecord.getStage());
        processStage.setRoleId(processStageRecord.getRoleId());
        return processStage;
    }

    @SuppressWarnings("Duplicates")
    public static ProcessStageRecord processStageToProcessStageRecordRecord(ProcessStage processStage) {
        ProcessStageRecord processStageRecord = new ProcessStageRecord();
        processStageRecord.setIdProcessStage(processStage.getIdProcessStage());
        processStageRecord.setProcessTypeId(processStage.getProcessTypeId());
        processStageRecord.setStage(processStage.getStage());
        processStageRecord.setRoleId(processStage.getRoleId());
        return processStageRecord;
    }

    @SuppressWarnings("Duplicates")
    public static ProcessType processTypeRecordYoProcessType(ProcessTypeRecord processTypeRecord) {
        ProcessType processType = new ProcessType();
        processType.setIdProcessType(processTypeRecord.getIdProcessType());
        processType.setName(processTypeRecord.getName());
        processType.setDescription(processTypeRecord.getDescription());
        processType.setTimeToDo(processTypeRecord.getTimeToDo());
        return processType;
    }

    @SuppressWarnings("Duplicates")
    public static ProcessTypeRecord processTypeToProcessTypeRecord(ProcessType processType) {
        ProcessTypeRecord processTypeRecord = new ProcessTypeRecord();
        processTypeRecord.setIdProcessType(processType.getIdProcessType());
        processTypeRecord.setName(processType.getName());
        processTypeRecord.setDescription(processType.getDescription());
        processTypeRecord.setTimeToDo(processType.getTimeToDo());
        return processTypeRecord;
    }

    @SuppressWarnings("Duplicates")
    public static Task taskRecordToTask(TaskRecord taskRecord) {
        Task task = new Task();
        task.setIdTask(taskRecord.getIdTask());
        task.setProcessId(taskRecord.getProcessId());
        task.setUserPerformerId(taskRecord.getUserPerformerId());
        task.setRolePerformerId(taskRecord.getRolePerformerId());
        task.setDateStart(taskRecord.getDateStart());
        task.setDateEndPlanning(taskRecord.getDateEndPlanning());
        task.setDateEndFact(taskRecord.getDateEndFact());
        task.setStatusId(taskRecord.getStatusId());
        return task;
    }

    @SuppressWarnings("Duplicates")
    public static TaskRecord taskToTaskRecord(Task task) {
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

    @SuppressWarnings("Duplicates")
    public static RoleQasRecord roleToRoleQasRecord(Role role) {
        RoleQasRecord roleQasRecord = new RoleQasRecord(
                role.getIdRole(),
                role.getName()
        );
        return roleQasRecord;
    }

    @SuppressWarnings("Duplicates")
    public static Role roleQasRecordToRole(RoleQasRecord roleQasRecord) {
        Role role = new Role();
        role.setIdRole(roleQasRecord.getIdRole());
        role.setName(roleQasRecord.getName());
        return role;
    }

    @SuppressWarnings("Duplicates")
    public static UserQasRecord userToUserQasRecord(User user) {
        UserQasRecord userQasRecord = new UserQasRecord(
                user.getIdUser(),
                user.getFio(),
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getTelegramChatId()
        );
        return userQasRecord;
    }

    @SuppressWarnings("Duplicates")
    public static User userQasRecordToUser(UserQasRecord userQasRecord){
        User user = new User();
        user.setIdUser(userQasRecord.getIdUser());
        user.setFio(userQasRecord.getFio());
        user.setLogin(userQasRecord.getLogin());
        user.setPassword(userQasRecord.getPassword());
        user.setEmail(userQasRecord.getEmail());
        user.setTelegramChatId(userQasRecord.getTelegramChatId());
        return user;
    }

    @SuppressWarnings("Duplicates")
    public static UserWithoutPassword userQasRecordToUserWithoutPassword(UserQasRecord userQasRecord){
        UserWithoutPassword user = new UserWithoutPassword();
        user.setIdUser(userQasRecord.getIdUser());
        user.setFio(userQasRecord.getFio());
        user.setLogin(userQasRecord.getLogin());
        user.setEmail(userQasRecord.getEmail());
        user.setTelegramChatId(userQasRecord.getTelegramChatId());
        return user;
    }
}
