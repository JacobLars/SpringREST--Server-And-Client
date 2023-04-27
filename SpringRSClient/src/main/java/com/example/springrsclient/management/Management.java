package com.example.springrsclient.management;

import com.example.springrsclient.model.Person;
import com.example.springrsclient.ui.UI;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Scanner;

public class Management {

    static boolean loop = true;
    static Scanner sc = new Scanner(System.in);
    static String baseUrl = "http://localhost:8080/";
    static RestTemplate restTemplate;


    public void runProgram() {
        while (loop) {
            UI.menu();
            int input = sc.nextInt();
            sc.nextLine();
            management(input);
        }
    }

    public void management(int input) {

        switch (input) {

            case 1:
                addPerson(createPerson());
                break;

            case 2:
                getAllPeople();
                break;

            case 3:
                System.out.println("id:");
                int id = sc.nextInt();
                sc.nextLine();
                findAndPrintPersonById(id);
                break;
            case 4:
                getAllPeople();
                System.out.println("id:");
                id = sc.nextInt();
                sc.nextLine();
                updateManagement(id);
                break;

            case 5:
                System.out.println("id:");
                id = sc.nextInt();
                sc.nextLine();
                deletePersonById(id);
                break;

            case 6:
                System.out.println("Enter search word:");
                String name = sc.nextLine();
                searchPeopleByName(name);

        }

    }

    public void updateManagement(int id) {

        UI.updateMenu();
        int input = sc.nextInt();
        sc.nextLine();

        switch (input) {
            case 1:
                System.out.println("New name:");
                String newName = sc.nextLine();
                updatePersonFirstNameById(id, newName);
                break;

            case 2:
                System.out.println("New lastname:");
                newName = sc.nextLine();
                updatePersonLastNameById(id, newName);
                break;

            case 3:
                System.out.println("New age:");
                int newAge = sc.nextInt();
                sc.nextLine();
                updatePersonAgeById(id, newAge);
                break;

            case 4:
                System.out.println("New Phone number:");
                String phoneNumber = sc.nextLine();
                updatePersonPhoneNumber(id, phoneNumber);
                break;
        }

    }

    public void updatePersonFirstNameById(int id, String name) {
        restTemplate = new RestTemplate();
        Person person = findPersonById(id);
        person.setFirstName(name);
        String rest = restTemplate.postForObject(baseUrl + "/" + "editperson/" + id, person, String.class);
        System.out.println(rest);

    }

    public void updatePersonPhoneNumber(int id, String phoneNumber) {
        restTemplate = new RestTemplate();
        Person person = findPersonById(id);
        person.setPhoneNumber(phoneNumber);
        String rest = restTemplate.postForObject(baseUrl + "/" + "editperson/" + id, person, String.class);
        System.out.println(rest);

    }

    public void updatePersonLastNameById(int id, String name) {
        restTemplate = new RestTemplate();
        Person person = findPersonById(id);
        person.setLastName(name);
        String rest = restTemplate.postForObject(baseUrl + "/" + "editperson/" + id, person, String.class);
        System.out.println(rest);
    }

    public void updatePersonAgeById(int id, int newAge) {
        restTemplate = new RestTemplate();
        Person person = findPersonById(id);
        person.setAge(newAge);
        String rest = restTemplate.postForObject(baseUrl + "/" + "editperson/" + id, person, String.class);
        System.out.println(rest);
    }

    private void addPerson(Person person) {
        restTemplate = new RestTemplate();
        String rest = restTemplate.postForObject(baseUrl + "/" + "addperson", person, String.class);
        System.out.println(rest);
    }

    private void getAllPeople() {
        restTemplate = new RestTemplate();
        ParameterizedTypeReference<List<Person>> listType =
                new ParameterizedTypeReference<List<Person>>() {
                };

        ResponseEntity<List<Person>> response = restTemplate.exchange(
                baseUrl + "/findall",
                HttpMethod.GET, null, listType);
        response.getBody().forEach(System.out::println);
    }

    private void searchPeopleByName(String name) {
        restTemplate = new RestTemplate();
        ParameterizedTypeReference<List<Person>> listType =
                new ParameterizedTypeReference<List<Person>>() {
                };

        ResponseEntity<List<Person>> response = restTemplate.exchange(
                baseUrl + "/searchperson/" + name,
                HttpMethod.GET, null, listType);
        response.getBody().forEach(System.out::println);
    }

    public void findAndPrintPersonById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        Person thePerson = restTemplate.getForObject(baseUrl + "/" + "findperson/" + id, Person.class);
        System.out.println(thePerson);
        sc.nextLine();
    }

    public Person findPersonById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        Person thePerson = restTemplate.getForObject(baseUrl + "/" + "findperson/" + id, Person.class);
        return thePerson;
    }

    public Person createPerson() {
        System.out.println("First name:");
        String firstName = sc.nextLine();

        System.out.println("Last name:");
        String lastName = sc.nextLine();

        System.out.println("Age:");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.println("Phone number:");
        String phoneNumber = sc.nextLine();

        Person person = new Person(firstName, lastName, age, phoneNumber);
        return person;
    }

    public void deletePersonById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        String rest = restTemplate.postForObject(baseUrl + "/delete/" + id, null, String.class);
        System.out.println(rest);
    }


}
