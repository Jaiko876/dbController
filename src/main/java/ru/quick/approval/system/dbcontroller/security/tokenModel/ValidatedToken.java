package ru.quick.approval.system.dbcontroller.security.tokenModel;

import lombok.Data;

@Data
public class ValidatedToken {
    private boolean validated;
    private String message;
}
