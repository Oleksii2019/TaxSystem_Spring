package ua.testing.registration_form.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.testing.registration_form.dto.NoteDTO;
import ua.testing.registration_form.entity.Taxpayer;

@Slf4j
@RestController
@RequestMapping(value = "/")
public class RegFormController {

    @Autowired
    IRegController rc;

    @RequestMapping(value = "not_format/users", method = RequestMethod.GET)
    public Iterable<Taxpayer> loadAllTaxpayers() {
        return rc.getAllTaxpayers();
    }

    @RequestMapping(value = "/reg_form", method = RequestMethod.POST)
    public void getUserFromRegForm(NoteDTO note){
        rc.fromRegForm(note);
        log.info("{}", note);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }

}
