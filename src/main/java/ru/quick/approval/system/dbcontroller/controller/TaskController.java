package ru.quick.approval.system.dbcontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.quick.approval.system.api.controller.TaskApi;
import ru.quick.approval.system.api.model.Task;
import ru.quick.approval.system.dbcontroller.service.TaskService;

import javax.validation.Valid;
import java.util.List;

/**
 * Рестовый контроллер для Task
 * @author Игорь М
 */

@RestController
public class TaskController implements TaskApi {

    private static final HttpStatus ERROR = HttpStatus.INTERNAL_SERVER_ERROR;
    private static final HttpStatus OK = HttpStatus.OK;
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ResponseEntity<List<Task>> getAllTask() {
        return new ResponseEntity<>(taskService.getAllTask(), OK);
    }

    @Override
    public ResponseEntity<Task> getTaskById(Integer id) {
        return new ResponseEntity<>(taskService.getTaskById(id), OK);
    }

    @Override
    public ResponseEntity<Void> updateTaskById(Integer id, @Valid Task task) {
        if (taskService.updateTaskById(id,task)) {
            return new ResponseEntity<>(OK);
        }
        return new ResponseEntity<>(ERROR);
    }

    @Override
    public ResponseEntity<Void> updateTaskByIdActive(Integer id) {
        if (taskService.updateTaskByIdActive(id)) {
            return new ResponseEntity<>(OK);
        }
        return new ResponseEntity<>(ERROR);
    }

    @Override
    public ResponseEntity<Void> updateTaskByIdAgreed(Integer id) {
        if (taskService.updateTaskByIdAgreed(id)) {
            return new ResponseEntity<>(OK);
        }
        return new ResponseEntity<>(ERROR);
    }

    @Override
    public ResponseEntity<Void> updateTaskByIdCanceled(Integer id) {
        if (taskService.updateTaskByIdCanceled(id)) {
            return new ResponseEntity<>(OK);
        }
        return new ResponseEntity<>(ERROR);
    }

    @Override
    public ResponseEntity<Void> updateTaskByIdDenied(Integer id) {
        if (taskService.updateTaskByIdDenied(id)) {
            return new ResponseEntity<>(OK);
        }
        return new ResponseEntity<>(ERROR);
    }

    @Override
    public ResponseEntity<Void> updateTaskByIdSended(Integer id) {
        if (taskService.updateTaskByIdSended(id)) {
            return new ResponseEntity<>(OK);
        }
        return new ResponseEntity<>(ERROR);
    }
}
