package ru.quick.approval.system.dbcontroller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jooq.demo.db.tables.records.ProcessRecord;

import java.sql.Date;

/**
 * Трансферный объект для таблицы Process
 * @author Kirill Mikheev
 * @version 1.0
 */

@Getter
@AllArgsConstructor
public class ProcessDto {

    private int id_process;
    private int process_type_id;
    private String name;
    private String description;
    private int user_start_id;
    private Date date_start;
    private Date date_end_planning;
    private Date date_end_fact;

    public ProcessDto(ProcessRecord processRecord) {
        this.id_process = processRecord.getIdProcess();
        this.process_type_id = processRecord.getProcessTypeId();
        this.name = processRecord.getName();
        this.description = processRecord.getDescription();
        this.user_start_id = processRecord.getUserStartId();
        this.date_start = processRecord.getDateStart();
        this.date_end_planning = processRecord.getDateEndPlanning();
        this.date_end_fact = processRecord.getDateEndPlanning();

    }
}
