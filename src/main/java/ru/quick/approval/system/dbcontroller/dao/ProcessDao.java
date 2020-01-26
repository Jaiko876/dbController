package ru.quick.approval.system.dbcontroller.dao;

import ru.quick.approval.system.dbcontroller.dao.iDao.IProcessDao;
import ru.quick.approval.system.dbcontroller.dto.ProcessDto;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.demo.db.tables.Process;
import org.jooq.demo.db.tables.records.ProcessRecord;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Даошка для работы с таблицей процессов
 * @author Kirill Mikheev
 * @version 1.0
 */

@Service
public class ProcessDao implements IProcessDao {

    /** Нужно для соединения и работы с бд*/
    private final DSLContext dslContext;

    public ProcessDao(DSLContext dslContext){
        this.dslContext = dslContext;
    }

    /**
     * Возвращает лист вссех процессов в таблице
     */
    @Override
    public List<ProcessDto> getProcesses() {
        List<ProcessDto> answer = new ArrayList<>();
        Result<ProcessRecord> records = dslContext.selectFrom(Process.PROCESS).fetch();
        for(ProcessRecord record : records){
            answer.add(new ProcessDto(record));
        }
        return answer;
    }

    /**
     * Возвращает процесс с заданным айдишником
     * @param id
     */
    @Override
    public ProcessDto getProcessById(int id) {
        //TODO: Сделать проверку на то, что объект был получен
        ProcessRecord record = dslContext.selectFrom(Process.PROCESS).where(Process.PROCESS.ID_PROCESS.eq(id)).fetchAny();
        return new ProcessDto(record);
    }

    /**
     * Добавляет новый процесс в таблицу
     * @param processDto
     * @return true, если все прошло успешно, иначе false
     */
    @Override
    public boolean insertProcess(ProcessDto processDto) {
        ProcessRecord record = new ProcessRecord(
                processDto.getId_process(),
                processDto.getProcess_type_id(),
                processDto.getName(),
                processDto.getDescription(),
                processDto.getUser_start_id(),
                processDto.getDate_start(),
                processDto.getDate_end_planning(),
                processDto.getDate_end_fact()
                );
        dslContext.insertInto(Process.PROCESS).values(record).execute();
        //TODO: Сделать проверку на то, что добавление прошло успешно

        return true;
    }


    /**
     * Удаляет процесс с заданным айдишником из таблицы
     * @param id
     * @return true, если все прошло успешно, иначе false
     */
    @Override
    public boolean deleteProcessById(int id) {
        dslContext.delete(Process.PROCESS).where(Process.PROCESS.ID_PROCESS.eq(id)).execute();
        //TODO: Сделать проверку на то, что удаление прошло успешно

        return true;
    }
}
