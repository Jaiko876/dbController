package ru.quick.approval.system.dbcontroller.service.iservice;

import ru.quick.approval.system.api.model.ProcessStage;

import java.util.List;

public interface IProcessStage {
    List<ProcessStage> getAllStages();
    ProcessStage getStageById(int id);
    boolean addStageByProcessType(int process_type_id, ProcessStage processStage);

}
