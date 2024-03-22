package com.itfactory.service;

import com.itfactory.model.Person;
import com.itfactory.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {


    private final PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person createPerson(Person newPerson) {
        return repository.save(newPerson);
    }

    public List<Person> aduToatePersoanele() {
        return repository.findAll();
    }

    public Person findById(Integer personId) {
        Optional<Person> byId = repository.findById(personId);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new IllegalArgumentException("Person with id " + personId + " not found.");
        }
    }

    public void deleteById(Integer personId) {
        Optional<Person> byId = repository.findById(personId);
        if (byId.isPresent()) {
            repository.deleteById(personId);
        } else {
            throw new IllegalArgumentException("Person with id " + personId + " not found.");
        }
    }
}
