package ru.quick.approval.system.dbcontroller.service.iservice;

import ru.quick.approval.system.api.model.ProcessType;

import java.util.List;

public interface IProcessType {
    List<ProcessType> getAllProcessTypes();
    boolean createNewProcessType(ProcessType processType);
    ProcessType getProcessTypeById(int id);
}
