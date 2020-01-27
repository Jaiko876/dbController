package ru.quick.approval.system.dbcontroller.service;

import org.jooq.demo.db.tables.records.ProcessStageRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.quick.approval.system.api.model.ProcessStage;
import ru.quick.approval.system.dbcontroller.dao.ProcessStageDao;
import ru.quick.approval.system.dbcontroller.service.iservice.IProcessStage;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с контроллером ProcessStageController
 * @author Игорь М
 */

@Service
public class ProcessStageService implements IProcessStage {
    private final ProcessStageDao processStageDao;

    static ProcessStage toPojo(ProcessStageRecord processStageRecord) {
        ProcessStage processStage = new ProcessStage();
        processStage.setIdProcessStage(processStageRecord.component1());
        processStage.setProcessTypeId(processStageRecord.component2());
        processStage.setStage(processStageRecord.component3());
        processStage.setRoleId(processStageRecord.component4());
        return processStage;
    }

    static ProcessStageRecord toRecord(ProcessStage processStage) {
        ProcessStageRecord processStageRecord = new ProcessStageRecord();
        processStageRecord.setIdProcessStage(processStage.getIdProcessStage());
        processStageRecord.setProcessTypeId(processStage.getProcessTypeId());
        processStageRecord.setStage(processStage.getStage());
        processStageRecord.setRoleId(processStage.getRoleId());
        return processStageRecord;
    }

    @Autowired
    private ProcessStageService(ProcessStageDao processStageDao) {
        this.processStageDao = processStageDao;
    }

    /**
     * Возвращает все Stage
     * @return - List<ProcessStage>
     */
    @Override
    public List<ProcessStage> getAllStages() {
        List<ProcessStage> processStageList = new ArrayList<>();
        List<ProcessStageRecord> allProcessStages = processStageDao.getAllProcessStages();
        for (ProcessStageRecord processStage: allProcessStages) {
            processStageList.add(toPojo(processStage));
        }
        return processStageList;
    }

    /**
     * Возвращает PrcessStage по id
     * @param id - id ProcessStage
     * @return - ProcessStage
     */
    @Override
    public ProcessStage getStageById(int id) {
        return toPojo(processStageDao.getProcessStageById(id));
    }

    /**
     * Добавляет новый ProcessStage с заданным process_type_id
     * @param process_type_id - id ProcessType
     * @param processStage - объект ProcessStage
     * @return - true/false
     */
    @Override
    public boolean addStageByProcessType(int process_type_id, ProcessStage processStage) {
        processStage.setProcessTypeId(process_type_id);
        return processStageDao.addProcessStage(toRecord(processStage));
    }
}
