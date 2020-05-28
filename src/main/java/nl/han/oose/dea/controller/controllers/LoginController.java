package nl.han.oose.dea.controller.controllers;

import javax.annotation.Resource;
import javax.inject.Inject;

import nl.han.oose.dea.controller.dto.LoginRespondeDTO;
import nl.han.oose.dea.datasource.dao.LoginDAO;
import nl.han.oose.dea.controller.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@RestController
@Service
@RequestMapping("/login")
@ComponentScan({"nl.han.oose.dea.datasource.dao"})
public class LoginController {

    private LoginDAO loginDAO;

    @Autowired
    public void setLoginDAO(LoginDAO loginDAO){
        this.loginDAO = loginDAO;
    }

    public LoginController() {
    }

    @GetMapping
    public @ResponseBody String hallo(){
        return"hallo";
    }
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "" , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<LoginRespondeDTO> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok().body(loginDAO.validateInfo(loginDTO));
    }
}
