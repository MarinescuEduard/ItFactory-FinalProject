package com.itfactory.controller;


import com.itfactory.model.Person;
import com.itfactory.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/addPerson")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person createdPerson = personService.createPerson(person);
        return ResponseEntity.ok(createdPerson);
    }

    @GetMapping("/getPersonList")
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> list = personService.getAllPersons();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> findPersonById(@PathVariable Integer personId) {
        try {
            Person person = personService.findById(personId);
            return ResponseEntity.ok(person);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer personId) {
        try {
            personService.deleteById(personId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{personId}")
    public ResponseEntity<Person> modifyNameById(@PathVariable Integer personId, @RequestBody String newPersonName) {
        try {
            Person person = personService.modifyById(personId, newPersonName);
            return ResponseEntity.ok(person);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/email/{personId}")
    public ResponseEntity<Person> modifyEmailById(@PathVariable Integer personId, @RequestBody String newEmail) {
        try {
            Person person = personService.modifyEmailById(personId, newEmail);
            return ResponseEntity.ok(person);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
