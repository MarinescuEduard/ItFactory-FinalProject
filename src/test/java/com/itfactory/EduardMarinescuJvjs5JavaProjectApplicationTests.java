package com.itfactory;

import com.itfactory.controller.PersonController;
import com.itfactory.model.Person;
import com.itfactory.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class EduardMarinescuJvjs5JavaProjectApplicationTests {

    @Mock
    private PersonService personService;
    @InjectMocks
    private PersonController personController;

    @Test
    void testAdaugaPersoana() {

        //Create a mock person
        Person person = new Person();
        person.setIdPerson(1);
        person.setFirstName("Eduard");
        person.setLastName("Marinescu");
        person.setEmailPerson("eduard.marinescu98@gmail.com");
        person.setAgePerson(26);

        //Mock the behaviour of the method .createPerson and return the mocked person
        when(personService.createPerson(person)).thenReturn(person);
        ResponseEntity<Person> responseEntity = personController.createPerson(person);

        //Check if the response status is OK and the created person is the mocked person
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(person, responseEntity.getBody());

    }


    @Test
    void testAduToatePersoanele() {

        //Create a mock list
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(3, "Rosy", "Rosy", "bobobobob@yahoo.com", 26));
        personList.add(new Person(1, "Eduard", "Marinescu", "eduard.marinescu98@gmail.com", 26));

        //Mock the behaviour of .aduToatePersoanele method
        when(personService.aduToatePersoanele()).thenReturn(personList);
        ResponseEntity<List<Person>> responseEntity = personController.aduToatePersoanele();

        //Check if the response status is OK and the created list includes the mocked persons
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(personList, responseEntity.getBody());
    }

    @Test
    void testFindPersonByID() {
        int personId = 1;

        //Create a mock person
        Person person = new Person();
        person.setIdPerson(1);
        person.setFirstName("Eduard");
        person.setLastName("Marinescu");
        person.setEmailPerson("eduard.marinescu98@gmail.com");
        person.setAgePerson(26);


        //Mock the behaviour of the .findById method
        when(personService.findById(personId)).thenReturn(person);
        ResponseEntity<Person> responseEntity = personController.findPersonById(personId);

        //Check if the response status is OK and the returned person is the mocked person
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(person, responseEntity.getBody());
    }

    @Test
    void testDeleteById() {
        int personId = 1;

        //Mock the behaviour of the .deleteById method
        doNothing().when(personService).deleteById(personId);
        ResponseEntity<Void> responseEntity = personController.deleteById(personId);

        //Verify if .deleteById was called with the correct argument
        verify(personService).deleteById(personId);
        //Verify the response status is NO CONTENT
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void testModifyLastNameById(){
        int personId=1;
        String newPersonLastName = "NewLastName";

        //Create a mock person
        Person person = new Person();
        person.setIdPerson(1);
        person.setFirstName("Eduard");
        person.setLastName("Marinescu");
        person.setEmailPerson("eduard.marinescu98@gmail.com");
        person.setAgePerson(26);

        //Mock the behaviour of .findById and .createPerson methods
        when(personService.findById(personId)).thenReturn(person);
        when(personService.createPerson(any(Person.class))).thenReturn(person);
        ResponseEntity<Person> responseEntity = personController.modifyNameById(personId,newPersonLastName);

        //Verify that findById was called with correct argument and if .createPerson was called with the mocked person.
        verify(personService).findById(personId);
        verify(personService).createPerson(person);

        //Verify the response status and if the new last name was modified
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newPersonLastName, responseEntity.getBody().getLastName());
    }

    @Test
    void testModifyEmailById(){
        int personId = 1;
        String newEmailAddress = "NewEmailAddress@gmail.com";

        //Create a mock person
        Person person = new Person();
        person.setIdPerson(1);
        person.setFirstName("Eduard");
        person.setLastName("Marinescu");
        person.setEmailPerson("eduard.marinescu98@gmail.com");
        person.setAgePerson(26);

        //Mock the behaviour of .findById and .createPerson methods
        when(personService.findById(personId)).thenReturn(person);
        when(personService.createPerson(any(Person.class))).thenReturn(person);
        ResponseEntity<Person> responseEntity = personController.modifyEmailById(personId, newEmailAddress);

        //Verify that findById was called with correct argument and if .createPerson was called with the mocked person.
        verify(personService).findById(personId);
        verify(personService).createPerson(person);

        //Verify the response status and if the new email was modified
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newEmailAddress, responseEntity.getBody().getEmailPerson());
    }
}
