package ru.quick.approval.system.dbcontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.quick.approval.system.api.controller.TypeApi;
import ru.quick.approval.system.api.model.ProcessType;
import ru.quick.approval.system.dbcontroller.service.ProcessTypeService;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер ProcessType
 * @author Игорь М
 */

@RestController
public class ProcessTypeController implements TypeApi {
    private final ProcessTypeService processTypeService;

    @Autowired
    public ProcessTypeController(ProcessTypeService processTypeService) {
        this.processTypeService = processTypeService;
    }

    @Override
    public ResponseEntity<Void> addProcessType(@Valid ProcessType processType) {
        if (processTypeService.createNewProcessType(processType))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @Override
    public ResponseEntity<List<ProcessType>> allProcessTypes() {
        return new ResponseEntity<>(processTypeService.getAllProcessTypes(), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<ProcessType> getProcessTypeById(Integer id) {
        return new ResponseEntity<>(processTypeService.getProcessTypeById(id), HttpStatus.ACCEPTED);
    }
}
