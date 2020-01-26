package ru.quick.approval.system.dbcontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.quick.approval.system.api.controller.UserApi;
import ru.quick.approval.system.api.model.*;
import ru.quick.approval.system.dbcontroller.service.iservice.IUserService;

import javax.validation.Valid;
import java.util.List;

/**
 * Рестовый контроллер для блока User
 * @author Kirill Mikheev
 * @version 1.0
 */

@RestController
public class UserController implements UserApi {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @Override
    public ResponseEntity<User> addRoleToUserById(Integer id, @Valid Role role) {
        return null;
    }

    @Override
    public ResponseEntity<User> addUser(@Valid User user) {
        return null;
    }

    @Override
    public ResponseEntity<List<UserWithoutPassword>> allUsers() {
        return new ResponseEntity<>(userService.allUsers(), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<List<Task>> getActiveTaskListOfUserById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Task>> getCmpleteTaskListOfUserById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Role>> getRoleListOfUserById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Task>> getTaskListOfUserById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Task>> getWaitTaskListOfUserById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<UserWithoutPassword> getUserById(Integer id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<User> login(@Valid InlineObject inlineObject) {
        return null;
    }

    @Override
    public ResponseEntity<User> logout() {
        return null;
    }

    @Override
    public ResponseEntity<UserWithoutPassword> updateUser(Integer id, @Valid UserWithoutPassword userWithoutPassword) {
        return null;
    }
}
