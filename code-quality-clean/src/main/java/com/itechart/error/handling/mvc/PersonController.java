package com.itechart.error.handling.mvc;

import com.itechart.error.handling.mvc.validation.NameNotStartsWithI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Validated
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/greet")
    public ResponseEntity<String> greet(@NotBlank @NameNotStartsWithI @RequestParam String name) {

        return ResponseEntity.ok("Hello "
                + personService.getPerson(name).getName());
    }
}
