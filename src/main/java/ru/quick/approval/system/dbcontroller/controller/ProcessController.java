package ru.quick.approval.system.dbcontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.quick.approval.system.api.controller.ProcessApi;
import ru.quick.approval.system.api.model.Process;
import ru.quick.approval.system.api.model.StatusType;
import ru.quick.approval.system.api.model.Task;
import ru.quick.approval.system.dbcontroller.service.ProcessService;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер ProcessController
 * @author Игорь М
 */

@RestController
public class ProcessController implements ProcessApi {
    private final ProcessService processService;

    @Autowired
    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }

    @Override
    public ResponseEntity<Void> createProcessByProcessType(Integer id, @Valid Process process) {
        if (processService.createNewProcessByProcessType(id, process))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @Override
    public ResponseEntity<Void> createProcessTaskByRoleId(Integer id, Integer roleId, @Valid Task task) {
        if (processService.createNewTaskByRoleId(id, roleId, task))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @Override
    public ResponseEntity<Void> createProcessTaskByUserId(Integer id, Integer userId, @Valid Task task) {
        if (processService.createNewTaskByUserId(id, userId, task))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @Override
    public ResponseEntity<List<Process>> getAllActiveProcess() {
        return new ResponseEntity<>(processService.getProcessStatusActive(),HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<List<Process>> getAllCompleteProcess() {
        return new ResponseEntity<>(processService.getProcessStatusComplete(),HttpStatus.ACCEPTED);

    }

    @Override
    public ResponseEntity<List<Process>> getAllProcess() {
        return new ResponseEntity<>(processService.getAllProcesses(),HttpStatus.ACCEPTED);

    }

    @Override
    public ResponseEntity<List<Task>> getAllTasksByThisId(Integer id) {
        return new ResponseEntity<>(processService.getAllTaskByProcessId(id),HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Process> getProcessById(Integer id) {
        return new ResponseEntity<>(processService.getProcessById(id),HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<StatusType> getProcessStatus(Integer id) {
        return new ResponseEntity<>(processService.getProcessStatusById(id),HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> updateProcessById(Integer id, @Valid Process process) {
        if (processService.updateProcessById(id, process))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}