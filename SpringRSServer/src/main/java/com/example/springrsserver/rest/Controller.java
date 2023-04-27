package com.example.springrsserver.rest;

import com.example.springrsserver.model.Person;
import com.example.springrsserver.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    PersonService personService;


    @PostMapping("/addperson")
    public ResponseEntity<String> savePerson(@RequestBody Person p){
        personService.savePerson(p);
        return ResponseEntity.accepted().body("The person has been added!");
    }

    @GetMapping("/findall")
    public List<Person> getAll(){
       return personService.findAll();
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable("id") Integer id){
        personService.deletePersonById(id);
        return ResponseEntity.accepted().body("The person has been deleted!");
    }

    @GetMapping("/findperson/{id}")
    public Person findPersonById(@PathVariable("id") Integer id){
      return personService.findPersonById(id);
    }

    @PostMapping("/editperson/{id}")
    public ResponseEntity<String> editPerson(@PathVariable("id") Integer id, @RequestBody Person p){
        Person thePerson = personService.findPersonById(id);
        personService.editPerson(thePerson, p);
        return ResponseEntity.accepted().body("The person has been edited!");
    }

    @GetMapping("/searchperson/{name}")
    public List<Person> findPersonByName(@PathVariable("name") String name){
        return personService.findPersonByName(name);
    }

}
