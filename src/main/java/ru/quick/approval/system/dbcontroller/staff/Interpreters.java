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
        process.setIdProcess(processRecord.component1());
        process.setProcessTypeId(processRecord.component2());
        process.setName(processRecord.component3());
        process.setDescription(processRecord.component4());
        process.setUserStartId(processRecord.component5());
        process.setDateStart(processRecord.component6());
        process.setDateEndPlanning(processRecord.component7());
        process.setDateEndFact(processRecord.component8());
        process.setStatusId(processRecord.component9());
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
        processStage.setIdProcessStage(processStageRecord.component1());
        processStage.setProcessTypeId(processStageRecord.component2());
        processStage.setStage(processStageRecord.component3());
        processStage.setRoleId(processStageRecord.component4());
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
        processType.setIdProcessType(processTypeRecord.component1());
        processType.setName(processTypeRecord.component2());
        processType.setDescription(processTypeRecord.component3());
        processType.setTimeToDo(processTypeRecord.component4());
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
}
