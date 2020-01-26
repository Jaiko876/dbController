package ru.quick.approval.system.dbcontroller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Трансферный объект для пользователя (не хранит в себе пароль)
 * @author Kirill Mikheev
 * @version 1.0
 */

@Getter
@AllArgsConstructor
public class UserDto {
    private int id_user;
    private String fio;
    private String login;
    private String email;
    private String telegram_chat_id;
}
