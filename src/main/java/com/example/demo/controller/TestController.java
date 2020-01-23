package com.example.demo.controller;

import com.example.demo.dao.RoleDAO;
import com.example.demo.pojo.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kirill Mikheev
 * @version 1.0
 */

@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    RoleDAO roleDAO;

    @RequestMapping(method = RequestMethod.GET, value = "/getrole")
    public RoleDTO get(int id) {
        return roleDAO.getRoleById(id);
    }
}
