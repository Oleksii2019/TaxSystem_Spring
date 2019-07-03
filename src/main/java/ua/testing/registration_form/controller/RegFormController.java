package ua.testing.registration_form.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.testing.registration_form.DAO.UserRepository;
import ua.testing.registration_form.dto.NoteDTO;
import ua.testing.registration_form.entity.User;
import ua.testing.registration_form.service.IRegFormControllerToService;

@Slf4j
@RestController
@RequestMapping(value = "/")
public class RegFormController {

    private IRegFormControllerToService r;
    private UserRepository db;

    @Autowired
    public RegFormController(IRegFormControllerToService r,
                             UserRepository db) {
        this.r = r;
        this.db = db;
    }

    @RequestMapping(value = "not_format/users", method = RequestMethod.GET)
    public Iterable<User> loadAllUsers() {
        return db.findAll();
    }

    @RequestMapping(value = "/reg_form", method = RequestMethod.POST)
    public void getRegFormController(NoteDTO note){
        r.fromRegFormController(note);
        log.info("{}", note);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
