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

    private static final HttpStatus ERROR = HttpStatus.INTERNAL_SERVER_ERROR;
    private static final HttpStatus OK = HttpStatus.OK;
    private IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Void> addRoleToUserById(Integer id, @Valid Role role) {
        ResponseEntity<Void> responseEntity;
        if(userService.addRoleToUserById(id, role)){
            responseEntity = new ResponseEntity<>(OK);
        }else{
            responseEntity = new ResponseEntity<>(ERROR);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Void> addUser(@Valid User user) {
        ResponseEntity<Void> responseEntity;
        if(userService.addUser(user)){
            responseEntity = new ResponseEntity<>(OK);
        }else {
            responseEntity = new ResponseEntity<>(ERROR);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<List<UserWithoutPassword>> allUsers() {
        return new ResponseEntity<>(userService.allUsers(), OK);
    }

    @Override
    public ResponseEntity<List<Task>> getActiveTaskListOfUserById(Integer id) {
        return new ResponseEntity<>(userService.getActiveTaskListOfUserById(id), OK);
    }

    @Override
    public ResponseEntity<List<Task>> getCompleteTaskListOfUserById(Integer id) {
        return new ResponseEntity<>(userService.getCompleteTaskListOfUserById(id), OK);
    }

    @Override
    public ResponseEntity<List<Task>> getWaitTaskListOfUserById(Integer id) {
        return new ResponseEntity<>(userService.getWaitTaskListOfUserById(id), OK);
    }

    @Override
    public ResponseEntity<List<Role>> getRoleListOfUserById(Integer id) {
        return new ResponseEntity<>(userService.getRoleListOfUserById(id), OK);
    }

    @Override
    public ResponseEntity<List<Task>> getTaskListOfUserById(Integer id) {
        return new ResponseEntity<>(userService.getTaskListOfUserById(id), OK);
    }

    @Override
    public ResponseEntity<UserWithoutPassword> getUserById(Integer id) {
        ResponseEntity<UserWithoutPassword> responseEntity;
        UserWithoutPassword userWithoutPassword = userService.getUserById(id);
        if(userWithoutPassword != null){
            responseEntity = new ResponseEntity<>(userWithoutPassword, OK);
        }else {
            responseEntity = new ResponseEntity<>(null, ERROR);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Boolean> login(@Valid InlineObject inlineObject) {
        return new ResponseEntity<>(userService.login(inlineObject), OK);
    }

    @Override
    public ResponseEntity<Void> updateUser(Integer id, @Valid User user) {
        ResponseEntity<Void> responseEntity;
        if(userService.updateUserById(id, user)){
            responseEntity = new ResponseEntity<>(OK);
        }else {
            responseEntity = new ResponseEntity<>(ERROR);
        }
        return responseEntity;
    }
}
