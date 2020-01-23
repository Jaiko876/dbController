package com.example.demo.controller;

import com.example.demo.dao.iDao.IRoleDao;
import com.example.demo.dto.RoleDTO;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Kirill Mikheev
 * @version 1.0
 */

@RestController
public class TestController {

    private final IRoleDao IRoleDao;

    public TestController(IRoleDao IRoleDao) {
        this.IRoleDao = IRoleDao;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/roles")
    public List<RoleDTO> getRoles() {
        return IRoleDao.getRoles();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/roles/{id}")
    public RoleDTO get(@PathVariable int id) {
        return IRoleDao.getRoleById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/roles")
    public boolean post(@RequestBody RoleDTO roleDTO) {
        return IRoleDao.insertRole(roleDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/roles/{id}")
    public boolean delete(@PathVariable int id) {
        return IRoleDao.deleteRoleById(id);
    }
}
