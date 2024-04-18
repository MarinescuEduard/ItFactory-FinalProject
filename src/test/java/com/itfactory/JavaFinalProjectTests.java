package com.itfactory;

import com.itfactory.controller.PersonController;
import com.itfactory.model.Person;
import com.itfactory.service.PersonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    private static int idPersonTest1;
    private static int idPersonTest2;
    private static int idPersonTest3;

    @BeforeEach
    void setUp() {
        Person person = new Person("Ion", "Mihai", "IonMihai@test.com", 22);
        Person person2 = new Person("Alex", "Marian", "AlexMarian@test.com", 26);
        Person person3 = new Person("Andrei", "Vasile", "AndreiVasile@test.com", 30);
        idPersonTest1 = personService.createPerson(person).getIdPerson();
        idPersonTest2 = personService.createPerson(person2).getIdPerson();
        idPersonTest3 = personService.createPerson(person3).getIdPerson();
    }

    @AfterEach
    void clearDatabase() {
        personController.deleteById(idPersonTest1);
        personController.deleteById(idPersonTest2);
        personController.deleteById(idPersonTest3);
    }

    @Test
    void testAddPerson() {
        Person person = new Person();
        person.setFirstName("Eduard");
        person.setLastName("Marinescu");
        person.setEmailPerson("eduard.marinescu98@gmail.com");
        person.setAgePerson(26);

        ResponseEntity<Person> responseEntity = personController.createPerson(person);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(person, responseEntity.getBody());
        System.out.println(responseEntity.getBody());
    }

    @Test
    void testFindPersonByID() {
        ResponseEntity<Person> responseEntity = personController.findPersonById(idPersonTest1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseEntity.getBody(), responseEntity.getBody());
    }

    @Test
    void testFindPersonNonExistentId() {
        Integer nonExistentId = 9999;

        ResponseEntity<Person> responseEntity = personController.findPersonById(nonExistentId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

  @Test
    void testDeleteByIdExistent() {
        ResponseEntity<Void> responseEntity = personController.deleteById(idPersonTest2);

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
        String newPersonLastName = "NewLastName";

        ResponseEntity<Person> responseEntity = personController.modifyNameById(idPersonTest3, newPersonLastName);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newPersonLastName, responseEntity.getBody().getLastName());
    }

    @Test
    void testModifyLastNameNonExistent() {
        Integer nonExistentId = 9999;

        ResponseEntity<Person> responseEntity = personController.modifyNameById(nonExistentId, "Ion");

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testModifyEmailById() {
        String newEmailAddress = "NewEmailAddress@gmail.com";

        ResponseEntity<Person> responseEntity = personController.modifyEmailById(idPersonTest3, newEmailAddress);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newEmailAddress, responseEntity.getBody().getEmailPerson());
    }

    @Test
    void testModifyEmailNonExistent() {
        Integer nonExistentId = 9999;

        ResponseEntity<Person> responseEntity = personController.modifyEmailById(nonExistentId, "test@test.com");

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}