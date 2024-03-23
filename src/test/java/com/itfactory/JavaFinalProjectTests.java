package com.itfactory;

import com.itfactory.controller.PersonController;
import com.itfactory.model.Person;
import com.itfactory.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class JavaFinalProjectTests {

    @Autowired
    private PersonController personController;

    @Autowired
    private PersonService personService;

    @Test
    void testAdaugaPersoana() {
        Person person = new Person();
        person.setFirstName("Eduard");
        person.setLastName("Marinescu");
        person.setEmailPerson("eduard.marinescu98@gmail.com");
        person.setAgePerson(26);

        ResponseEntity<Person> responseEntity = personController.createPerson(person);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(person, responseEntity.getBody());
    }

    @Test
    void testFindPersonByID() {
        int personId = 1;

        Person person = new Person();
        person.setIdPerson(1);
        person.setFirstName("Ion");
        person.setLastName("Mihai");
        person.setEmailPerson("IonMihai@test.com");
        person.setAgePerson(22);

        ResponseEntity<Person> responseEntity = personController.findPersonById(personId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(person, responseEntity.getBody());
    }

    @Test
    void testFindPersonNonExistentId() {
        Integer nonExistentId = 9999;

        ResponseEntity<Person> responseEntity = personController.findPersonById(nonExistentId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteByIdExistent() {
        int personId = 2;

        ResponseEntity<Void> responseEntity = personController.deleteById(personId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteNonExistentId() {
        Integer nonExistentId = 9999;

        ResponseEntity<Void> responseEntity = personController.deleteById(nonExistentId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testModifyLastNameById() {
        int personId = 1;
        String newPersonLastName = "NewLastName";

        ResponseEntity<Person> responseEntity = personController.modifyNameById(personId, newPersonLastName);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newPersonLastName, responseEntity.getBody().getLastName());
    }

    @Test
    void testModifyEmailById() {
        int personId = 1;
        String newEmailAddress = "NewEmailAddress@gmail.com";

        ResponseEntity<Person> responseEntity = personController.modifyEmailById(personId, newEmailAddress);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newEmailAddress, responseEntity.getBody().getEmailPerson());
    }
}