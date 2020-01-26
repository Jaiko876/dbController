package ru.quick.approval.system.dbcontroller.dao.iDao;

import org.jooq.demo.db.tables.records.ProcessRecord;

import java.util.List;

/**
 * Интерфейс DAO-класса для работы с таблицей process
 * @author Kirill Mikheev
 * @version 1.0
 */

public interface IProcessDao {

    List<ProcessRecord> getAllProcesses();

    ProcessRecord getProcessById(int id);

    boolean updateProcessById(int id, ProcessRecord newProcess);

    boolean addProcess(ProcessRecord newProcess);
}
