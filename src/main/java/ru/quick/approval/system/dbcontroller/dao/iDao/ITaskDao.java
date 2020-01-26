package ru.quick.approval.system.dbcontroller.dao.iDao;

import org.jooq.demo.db.tables.records.TaskRecord;

import java.util.List;

/**
 * Интерфейс DAO-класса для работы с таблицей task
 * @author Kirill Mikheev
 * @version 1.0
 */

public interface ITaskDao {

    List<TaskRecord> getAllTasks();

    TaskRecord getTaskById(int id);

    boolean updateTaskById(int id, TaskRecord newTask);

    boolean addTask(TaskRecord newTask);
}
