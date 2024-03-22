package com.itfactory;

import com.itfactory.controller.PersonController;
import com.itfactory.model.Person;
import com.itfactory.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class EduardMarinescuJvjs5JavaProjectApplicationTests {

    @Autowired
    private PersonController personController;

    @Autowired
    private PersonService personService;

    @Test
    void testAdaugaPersoana() {
        Person person = new Person();
        person.setIdPerson(1);
        person.setFirstName("Eduard");
        person.setLastName("Marinescu");
        person.setEmailPerson("eduard.marinescu98@gmail.com");
        person.setAgePerson(26);

        ResponseEntity<Person> responseEntity = personController.createPerson(person);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(person, responseEntity.getBody());
    }

    @Test
    void testAduToatePersoanele() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(3, "Rosy", "Rosy", "bobobobob@yahoo.com", 26));
        personList.add(new Person(1, "Eduard", "Marinescu", "eduard.marinescu98@gmail.com", 26));

        ResponseEntity<List<Person>> responseEntity = personController.aduToatePersoanele();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(personList, responseEntity.getBody());
    }

    @Test
    void testFindPersonByID() {
        int personId = 1;

        Person person = new Person();
        person.setIdPerson(1);
        person.setFirstName("Eduard");
        person.setLastName("Marinescu");
        person.setEmailPerson("eduard.marinescu98@gmail.com");
        person.setAgePerson(26);

        ResponseEntity<Person> responseEntity = personController.findPersonById(personId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(person, responseEntity.getBody());
    }

    @Test
    void testDeleteById() {
        int personId = 1;

        ResponseEntity<Void> responseEntity = personController.deleteById(personId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void testModifyLastNameById() {
        int personId = 1;
        String newPersonLastName = "NewLastName";

        Person person = new Person();
        person.setIdPerson(1);
        person.setFirstName("Eduard");
        person.setLastName("Marinescu");
        person.setEmailPerson("eduard.marinescu98@gmail.com");
        person.setAgePerson(26);

        Optional<Person> optionalPerson = Optional.of(person);

        ResponseEntity<Person> responseEntity = personController.modifyNameById(personId, newPersonLastName);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newPersonLastName, responseEntity.getBody().getLastName());
    }

    @Test
    void testModifyEmailById() {
        int personId = 1;
        String newEmailAddress = "NewEmailAddress@gmail.com";

        Person person = new Person();
        person.setIdPerson(1);
        person.setFirstName("Eduard");
        person.setLastName("Marinescu");
        person.setEmailPerson("eduard.marinescu98@gmail.com");
        person.setAgePerson(26);

        Optional<Person> optionalPerson = Optional.of(person);

        ResponseEntity<Person> responseEntity = personController.modifyEmailById(personId, newEmailAddress);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newEmailAddress, responseEntity.getBody().getEmailPerson());
    }
}