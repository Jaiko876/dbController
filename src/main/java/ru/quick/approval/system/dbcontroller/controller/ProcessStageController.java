package ru.quick.approval.system.dbcontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.quick.approval.system.api.controller.StageApi;
import ru.quick.approval.system.api.model.ProcessStage;
import ru.quick.approval.system.dbcontroller.service.ProcessStageService;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер ProcessStage
 * @author Игорь М
 */

@RestController
public class ProcessStageController implements StageApi {
    private final ProcessStageService processStageService;

    @Autowired
    public ProcessStageController(ProcessStageService processStageService) {
        this.processStageService = processStageService;
    }

    @Override
    public ResponseEntity<Void> addProcessStageByTypeId(Integer id, @Valid ProcessStage processStage) {
        if (processStageService.addStageByProcessType(id, processStage))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @Override
    public ResponseEntity<List<ProcessStage>> allProcessStages() {
        return new ResponseEntity<>(processStageService.getAllStages(), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<ProcessStage> getProcessStageById(Integer id) {
        return new ResponseEntity<>(processStageService.getStageById(id), HttpStatus.ACCEPTED);
    }
}