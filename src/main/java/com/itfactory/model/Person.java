package com.itfactory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;


@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPerson;
    private String firstName;
    private String lastName;
    private String emailPerson;
    private Integer agePerson;

    public Person(int i, String eduard, String marinescu, String mail, int i1) {
    }

    public Person() {
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailPerson(String emailPerson) {
        this.emailPerson = emailPerson;
    }

    public void setAgePerson(Integer agePerson) {
        this.agePerson = agePerson;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailPerson() {
        return emailPerson;
    }

    public Integer getAgePerson() {
        return agePerson;
    }

    @Override
    public String toString() {
        return "Person{" +
                "idPerson=" + idPerson +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailPerson='" + emailPerson + '\'' +
                ", agePerson=" + agePerson +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(idPerson, person.idPerson) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(emailPerson, person.emailPerson) && Objects.equals(agePerson, person.agePerson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPerson, firstName, lastName, emailPerson, agePerson);
    }
}
