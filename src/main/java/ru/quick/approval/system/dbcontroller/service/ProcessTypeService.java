package ru.quick.approval.system.dbcontroller.service;

import org.jooq.demo.db.tables.records.ProcessTypeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.quick.approval.system.api.model.ProcessType;
import ru.quick.approval.system.dbcontroller.dao.ProcessTypeDao;
import ru.quick.approval.system.dbcontroller.service.iservice.IProcessType;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с контроллером ProcessTypeController
 * @author Игорь М
 */

@Service
public class ProcessTypeService implements IProcessType {
    private final ProcessTypeDao processTypeDao;

    @Autowired
    private ProcessTypeService(ProcessTypeDao processTypeDao) {
        this.processTypeDao = processTypeDao;
    }

    static ProcessType toPojo(ProcessTypeRecord processTypeRecord) {
        ProcessType processType = new ProcessType();
        processType.setIdProcessType(processTypeRecord.component1());
        processType.setName(processTypeRecord.component2());
        processType.setDescription(processTypeRecord.component3());
        processType.setTimeToDo(processTypeRecord.component4());
        return processType;
    }
    static ProcessTypeRecord toRecord(ProcessType processType) {
        ProcessTypeRecord processTypeRecord = new ProcessTypeRecord();
        processTypeRecord.setIdProcessType(processType.getIdProcessType());
        processTypeRecord.setName(processType.getName());
        processTypeRecord.setDescription(processType.getDescription());
        processTypeRecord.setTimeToDo(processType.getTimeToDo());
        return processTypeRecord;
    }

    /**
     * Возвращает все ProcessTypes
     * @return List<ProcessTypes>
     */
    @Override
    public List<ProcessType> getAllProcessTypes() {
        List<ProcessType> processTypeList = new ArrayList<>();
        List<ProcessTypeRecord> allProcessTypes = processTypeDao.getAllProcessTypes();
        for (ProcessTypeRecord processTypeRecord: allProcessTypes) {
            processTypeList.add(toPojo(processTypeRecord));
        }
        return processTypeList;
    }

    /**
     * Создаёт новый ProcessType
     * @param processType - объект типа processType
     * @return - true/false
     */
    @Override
    public boolean createNewProcessType(ProcessType processType) {
        return processTypeDao.addProcessType(toRecord(processType));
    }

    /**
     * Возвращает ProcessType по id
     * @param id - id ProcessType
     * @return - ProcessType
     */
    @Override
    public ProcessType getProcessTypeById(int id) {
        return toPojo(processTypeDao.getProcessTypeById(id));
    }
}
