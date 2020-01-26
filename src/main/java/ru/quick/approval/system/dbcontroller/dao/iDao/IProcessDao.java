package ru.quick.approval.system.dbcontroller.dao.iDao;

import ru.quick.approval.system.dbcontroller.dto.ProcessDto;

import java.util.List;

/**
 * Интерфейс для даошки, работающей с таблицей процессов
 * @author Kirill Mikheev
 * @version 1.0
 */

public interface IProcessDao {

    /**
     * Возвращает лист вссех процессов в таблице
     */
    List<ProcessDto> getProcesses();

    /**
     * Возвращает процесс с заданным айдишником
     * @param id
     */
    ProcessDto getProcessById(int id);

    /**
     * Добавляет новый процесс в таблицу
     * @param processDto
     * @return true, если все прошло успешно, иначе false
     */
    boolean insertProcess(ProcessDto processDto);

    /**
     * Удаляет процесс с заданным айдишником из таблицы
     * @param id
     * @return true, если все прошло успешно, иначе false
     */
    boolean deleteProcessById(int id);
}
