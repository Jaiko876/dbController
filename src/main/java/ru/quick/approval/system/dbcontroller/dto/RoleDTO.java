package ru.quick.approval.system.dbcontroller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jooq.demo.db.tables.records.RoleRecord;

/**
 * Трансферный объект для работы с таблицей Role
 * @author Kirill Mikheev
 * @version 1.0
 */

@Getter
@AllArgsConstructor
public class RoleDTO {
    private int roleId;
    private String name;

    public RoleDTO(RoleRecord roleRecord){
        this.roleId = roleRecord.getRoleId();
        this.name = roleRecord.getName();
    }
}
