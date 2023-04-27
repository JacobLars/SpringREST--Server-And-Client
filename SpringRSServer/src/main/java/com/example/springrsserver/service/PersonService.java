package com.example.springrsserver.service;

import com.example.springrsserver.dao.PersonDao;
import com.example.springrsserver.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonService {

    @Autowired
    PersonDao personDao ;

    public void savePerson(Person person){
        personDao.save(person);
    }

    public List<Person> findAll(){

        return (List<Person>) personDao.findAll();
    }

    public void deletePersonById(Integer id){
        personDao.delete(findPersonById(id));
    }

    public Person findPersonById(Integer id){
       return personDao.findById(id).get();
    }

    public void editPerson(Person p1, Person p2){
        p1.setFirstName(p2.getFirstName());
        p1.setLastName(p2.getLastName());
        p1.setAge(p2.getAge());
        p1.setPhoneNumber(p2.getPhoneNumber());
        personDao.save(p1);
    }

    public List<Person> findPersonByName(String name){
           return personDao.findPersonByName(name);
    }
}
