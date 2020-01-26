package ru.quick.approval.system.dbcontroller.dao;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.demo.db.tables.records.ProcessRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.quick.approval.system.dbcontroller.dao.iDao.IProcessDao;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.demo.db.tables.Process.PROCESS;


/**
 * DAO-класс для взаимодействия с таблицей process
 * @author Kirill Mikheev
 * @version 1.0
 */

@Component
public class ProcessDao implements IProcessDao {

    /** Нужно для соединения и работы с бд*/
    private final DSLContext dslContext;

    @Autowired
    public ProcessDao(DSLContext dslContext){
        this.dslContext = dslContext;
    }

    /**
     * Возвращает список всех процессов
     * @return List<ProcessRecord>
     */
    @Override
    public List<ProcessRecord> getAllProcesses() {
        List<ProcessRecord> answer = new ArrayList<>();
        Result<ProcessRecord> result = dslContext.selectFrom(PROCESS).fetch();
        for (ProcessRecord record : result){
            answer.add(record);
        }
        return answer;
    }

    /**
     * Возвращает процесс с заданным id
     * @param id
     * @return ProcessRecord
     */
    @Override
    public ProcessRecord getProcessById(int id) {
        return dslContext.selectFrom(PROCESS).where(PROCESS.ID_PROCESS.eq(id)).fetchAny();
    }

    /**
     * Обновляет данные для процесса по его id
     * @param id
     * @param newProcess объект с новыми данными для процесса
     * @return true, если все прошло успешно, иначе false
     */
    @Override
    public boolean updateProcessById(int id, ProcessRecord newProcess) {
        int response = dslContext.update(PROCESS).
                set(PROCESS.PROCESS_TYPE_ID, newProcess.getProcessTypeId()).
                set(PROCESS.NAME, newProcess.getName()).
                set(PROCESS.DESCRIPTION, newProcess.getDescription()).
                set(PROCESS.USER_START_ID, newProcess.getUserStartId()).
                set(PROCESS.DATE_START, newProcess.getDateStart()).
                set(PROCESS.DATE_END_PLANNING, newProcess.getDateEndPlanning()).
                set(PROCESS.DATE_END_FACT, newProcess.getDateEndFact()).
                set(PROCESS.STATUS_ID, newProcess.getStatusId()).
                where(PROCESS.ID_PROCESS.eq(id)).execute();
        return response == 0;
    }

    /**
     * Добавляет новый процесс
     * @param newProcess
     * @return true, если все прошло успено, иначе false
     */
    @Override
    public boolean addProcess(ProcessRecord newProcess) {
        int response = dslContext.insertInto(
                PROCESS, PROCESS.PROCESS_TYPE_ID, PROCESS.NAME, PROCESS.DESCRIPTION, PROCESS.USER_START_ID,
                PROCESS.DATE_START, PROCESS.DATE_END_PLANNING, PROCESS.DATE_END_FACT, PROCESS.STATUS_ID)
                .values(newProcess.getProcessTypeId(), newProcess.getName(), newProcess.getDescription(),
                newProcess.getUserStartId(), newProcess.getDateStart(), newProcess.getDateEndPlanning(),
                newProcess.getDateEndFact(), newProcess.getStatusId())
                .execute();
        return response == 0;
    }
}
